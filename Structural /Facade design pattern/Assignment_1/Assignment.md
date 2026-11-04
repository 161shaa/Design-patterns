# 🎯 Assignment: Loan Processing — Facade, Builder & Orchestrator
Domain: Same loan system we've been working with. Difficulty: Intermediate → Advanced.
Time: ~2–3 hours. Rules: No looking at previous code. Re‑derive from memory.
Save as: assignment-loan-facade.md

📖 Scenario
You are rebuilding the backend of LoanPro, a lending platform. The loan lifecycle spans eligibility, pricing, approval, disbursement, and compliance. Different internal teams interact with the flow:

Customer mobile/web app — "Am I eligible? What's my offer?"

Underwriter console — approve / reject applications

Finance ops — disburse funds

Compliance team — audit trail + regulatory reporting

The original LoanProcessingFacade dumped all 20 subsystems into one class with ~25 public methods. It has become unmaintainable. Your job: rebuild it correctly using everything you learned about Facade, Builder, God Objects, and Orchestrators.

🧩 The 20 Subsystems (assume all exist with no‑arg constructors)
text
ELIGIBILITY
  1. CreditScoreCheck          boolean checkScore(String pan)
  2. DocumentVerification      boolean verifyDocuments(List<String> docs)
  3. IncomeVerification        boolean verifyIncome(double income)
  4. EmploymentVerification    boolean verifyEmployment(String employer)
  5. AddressVerification       boolean verifyAddress(Address addr)
  6. KycVerification           boolean verifyKyc(String pan, String aadhaar)
  7. FraudCheck                boolean checkFraud(String pan)

PRICING
  8. LoanAmountCalculator      double  calculate(double income)
  9. InterestRateCalculator    double  calculate(int creditScore)
 10. EmiCalculator             double  calculate(double principal, double rate, int months)

APPROVAL
 11. RiskAssessmentEngine      String  assess(LoanApplication app)
 12. ApprovalWorkflow          boolean approve(String risk)
 13. CollateralValuation       double  valuate(String collateralType)

DISBURSEMENT
 14. BankAccountVerifier       boolean verify(String account)
 15. AgreementGenerator        String  generate(LoanApplication app)
 16. ESignService              boolean sign(String agreement)
 17. DisbursementService       void    disburse(String account, double amount)
 18. NotificationService       void    notify(String recipient, String msg)

COMPLIANCE
 19. AuditLogger               void    log(String event)
 20. RegulatoryReporter        void    report(String event)
🏗️ PART A — Warm‑up: The Simple Facade (25 pts)
Rebuild from memory the simple LoanCheckFacade we built together — without looking at the previous code.

Deliverable: LoanCheckFacade with one method:

java
void loanChecker()
It must:

Use exactly 3 subsystems: CreditScoreCheck, DocumentVerification, LoanStatus

Print "Loan Approved" in a bordered block if both checks pass

Print "Loan Rejected" in a bordered block otherwise

Be small — one method, three dependencies, one client (the customer app)

Write a comment above the class answering: Which client uses this facade? What did the client avoid having to know?

Also build: LoanStatus — a class with a void status() method that prints the "Loan Approved" block.

🏗️ PART B — Builder: LoanApplication object (25 pts)
LoanApplication has ~20 fields (one per subsystem's needs). Build it correctly.

Required fields:

text
applicant, panNumber, accountNumber,
income, requestedAmount
Optional fields (with sensible defaults):

text
tenureMonths         (60)
employmentType       ("UNKNOWN")
city                 ("")
age                  (0)
education            ("")
hasExistingLoan      (false)
isDefaulter          (false)
existingEmi          (0.0)
collateralType       (null)
collateralValue      (0.0)
coApplicant          (null)
purpose              (null)
referenceName        (null)
aadhaarNumber        (null)
(That's 15 optional — add a few more if you want to hit 20 exactly.)

Requirements:

LoanApplication must be final and immutable — no setters, all fields final

Constructor must be private — only the Builder can create it

LoanApplication.builder()...build() fluent API

build() must be the single validation checkpoint — enforce at least:

applicant non‑null and non‑empty

panNumber matches regex [A-Z]{5}[0-9]{4}[A-Z]

income > 0 and requestedAmount > 0

age between 18 and 70 (if provided)

tenureMonths >= 6

Cross‑field rule: if isDefaulter == true → reject outright

Cross‑field rule: if existingEmi > income * 0.5 → reject ("existing EMI too high for income")

Throw IllegalArgumentException / NullPointerException with clear messages on failure

Do NOT use Lombok yet — write the manual version first. (Try Lombok in the bonus.)

Test in main(): Build two valid LoanApplication objects and two that fail validation (different failure reasons). Print the exception messages.

🏗️ PART C — The God Object → Split (40 pts) 🔥 The main event
The existing LoanProcessingFacade has all 20 subsystems and ~25 public methods. Fix it.

Step C1 — Diagnose (write this as a comment block at the top of your file)
Answer in your own words:

Why is LoanProcessingFacade a God Object? Name three specific symptoms (not just "too big").

How many dependencies does it have? How many public methods (roughly)?

Apply the "different clients" test: which clients use disjoint subsets of its methods? List them.

Step C2 — The Method × Client matrix
Fill this in (extend it — add rows for every plausible method):

Method	Customer app	Underwriter	Finance ops	Compliance
checkEligibility()	✅			
quote()	✅			
approve()		✅		
disburse()			✅	
fileReport()				✅
...				
The columns are your facades. Justify your splits based on the columns.

Step C3 — Build 5 split facades
Create these 5 classes (each with 2–6 dependencies — no more, no less):

Facade	Purpose	Subsystems to hide
LoanEligibilityFacade	Customer eligibility check	5–7 eligibility subsystems — you decide which
LoanPricingFacade	Quote generation	2–4 pricing subsystems
LoanApprovalFacade	Underwriter approval decision	2–4 approval subsystems
LoanDisbursementFacade	Finance ops disbursement	3–6 disbursement subsystems
LoanComplianceFacade	Audit + reporting	2–3 compliance subsystems
Hard rules:

✅ Each facade's dependencies must be private final

✅ Each facade has ≤ 3 public methods

✅ Each facade's javadoc says: "Used by: <client>"

❌ No facade may reference another facade (they must be independent)

❌ No facade may contain business logic from a different stage (e.g. LoanApprovalFacade must not calculate EMI)

Step C4 — The Thin Orchestrator
LoanApplicationOrchestrator only sequences the 5 facades:

text
eligibility check → pricing quote → approval decision
                  → disbursement → compliance record
Hard rules:

❌ No business logic — no score thresholds, no interest math, no domain rules

✅ Only if (!result.isEligible()) return; style sequencing

✅ All 5 facades injected as private final fields (constructor injection)

✅ One public method: placeApplication(LoanApplication app)

Step C5 — Client demos
Write four separate "client" classes:

java
class CustomerApp         { /* only uses eligibility + pricing */ }
class UnderwriterConsole  { /* only uses approval + compliance */ }
class FinanceConsole      { /* only uses disbursement */ }
class ComplianceDashboard { /* only uses compliance */ }
Each client must have a compile‑time dependency on ONLY the facades it needs.

In main(), demonstrate the payoff: CustomerApp has zero references to LoanDisbursementFacade, ESignService, RegulatoryReporter, etc. If you deleted LoanDisbursementFacade.java, would CustomerApp still compile? It should.
