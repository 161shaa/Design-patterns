import java.util.List;
import java.util.Objects;

// ============================================================
// PART A: SIMPLE FACADE (from memory)
// Used by: Customer mobile app — "Am I eligible?"
// Client avoids knowing: the 3 subsystem classes, the correct
// order of checks, and the print formatting.
// ============================================================

class CreditScoreCheck {
    boolean checkScore() { return true; }
    // Part C overload — accepts PAN
    boolean checkScore(String pan) { return pan != null && !pan.isBlank(); }
}

class DocumentVerification {
    boolean verifyDocuments() { return true; }
    // Part C overload — accepts doc list
    boolean verifyDocuments(List<String> docs) { return docs != null && !docs.isEmpty(); }
}

class LoanStatus {
    void status() {
        System.out.println("---------------------------------");
        System.out.println("Loan Approved");
        System.out.println("---------------------------------");
    }
}

class LoanCheckFacade {
    // Used by: Customer mobile app
    private CreditScoreCheck     creditScoreCheck;
    private DocumentVerification documentVerification;
    private LoanStatus           loanStatus;

    public LoanCheckFacade() {
        this.creditScoreCheck     = new CreditScoreCheck();
        this.documentVerification = new DocumentVerification();
        this.loanStatus           = new LoanStatus();
    }

    void loanChecker() {
        if (this.creditScoreCheck.checkScore()
                && this.documentVerification.verifyDocuments()) {
            this.loanStatus.status();
        } else {
            System.out.println("---------------------------------");
            System.out.println("Loan Rejected");
            System.out.println("---------------------------------");
        }
    }
}

// ============================================================
// PART B: BUILDER — LoanApplication (20 fields, immutable)
// ============================================================

final class LoanApplication {

    // ---- Required (5) ----
    private final String applicant;
    private final String panNumber;
    private final String accountNumber;
    private final double income;
    private final double requestedAmount;

    // ---- Optional (15) ----
    private final int     tenureMonths;
    private final String  employmentType;
    private final String  city;
    private final int     age;
    private final String  education;
    private final boolean hasExistingLoan;
    private final boolean isDefaulter;
    private final double  existingEmi;
    private final String  collateralType;
    private final double  collateralValue;
    private final String  coApplicant;
    private final String  purpose;
    private final String  referenceName;
    private final String  aadhaarNumber;
    private final String  employer;

    // ---- PRIVATE constructor: only Builder can call it ----
    private LoanApplication(Builder b) {
        this.applicant       = b.applicant;
        this.panNumber       = b.panNumber;
        this.accountNumber   = b.accountNumber;
        this.income          = b.income;
        this.requestedAmount = b.requestedAmount;
        this.tenureMonths    = b.tenureMonths;
        this.employmentType  = b.employmentType;
        this.city            = b.city;
        this.age             = b.age;
        this.education       = b.education;
        this.hasExistingLoan = b.hasExistingLoan;
        this.isDefaulter     = b.isDefaulter;
        this.existingEmi     = b.existingEmi;
        this.collateralType  = b.collateralType;
        this.collateralValue = b.collateralValue;
        this.coApplicant     = b.coApplicant;
        this.purpose         = b.purpose;
        this.referenceName   = b.referenceName;
        this.aadhaarNumber   = b.aadhaarNumber;
        this.employer        = b.employer;
    }

    // ---- Getters (subset shown) ----
    public String applicant()       { return applicant; }
    public String panNumber()       { return panNumber; }
    public String accountNumber()   { return accountNumber; }
    public double income()          { return income; }
    public double requestedAmount() { return requestedAmount; }
    public int    tenureMonths()    { return tenureMonths; }
    public String employer()        { return employer; }

    public static Builder builder() { return new Builder(); }

    @Override public String toString() {
        return "LoanApplication{applicant='" + applicant + "', income=" + income +
               ", requested=" + requestedAmount + ", tenure=" + tenureMonths + "}";
    }

    // ============================================================
    // BUILDER
    // ============================================================
    public static final class Builder {

        private String  applicant;
        private String  panNumber;
        private String  accountNumber;
        private double  income;
        private double  requestedAmount;

        private int     tenureMonths    = 60;
        private String  employmentType  = "UNKNOWN";
        private String  city            = "";
        private int     age             = 0;         // 0 = not provided
        private String  education       = "";
        private boolean hasExistingLoan = false;
        private boolean isDefaulter     = false;
        private double  existingEmi     = 0.0;
        private String  collateralType  = null;
        private double  collateralValue = 0.0;
        private String  coApplicant     = null;
        private String  purpose         = null;
        private String  referenceName   = null;
        private String  aadhaarNumber   = null;
        private String  employer        = null;

        private Builder() {}

        // ---- Fluent setters ----
        public Builder applicant(String v)        { this.applicant = v;       return this; }
        public Builder panNumber(String v)        { this.panNumber = v;       return this; }
        public Builder accountNumber(String v)    { this.accountNumber = v;   return this; }
        public Builder income(double v)           { this.income = v;          return this; }
        public Builder requestedAmount(double v)  { this.requestedAmount = v; return this; }
        public Builder tenureMonths(int v)        { this.tenureMonths = v;    return this; }
        public Builder employmentType(String v)   { this.employmentType = v;  return this; }
        public Builder city(String v)             { this.city = v;            return this; }
        public Builder age(int v)                 { this.age = v;             return this; }
        public Builder education(String v)        { this.education = v;       return this; }
        public Builder hasExistingLoan(boolean v) { this.hasExistingLoan = v; return this; }
        public Builder isDefaulter(boolean v)     { this.isDefaulter = v;     return this; }
        public Builder existingEmi(double v)      { this.existingEmi = v;     return this; }
        public Builder collateralType(String v)   { this.collateralType = v;  return this; }
        public Builder collateralValue(double v)  { this.collateralValue = v; return this; }
        public Builder coApplicant(String v)      { this.coApplicant = v;     return this; }
        public Builder purpose(String v)          { this.purpose = v;         return this; }
        public Builder referenceName(String v)    { this.referenceName = v;   return this; }
        public Builder aadhaarNumber(String v)    { this.aadhaarNumber = v;   return this; }
        public Builder employer(String v)         { this.employer = v;        return this; }

        // ---- SINGLE validation checkpoint ----
        public LoanApplication build() {
            // Required
            Objects.requireNonNull(applicant, "applicant is required");
            if (applicant.trim().isEmpty()) throw new IllegalArgumentException("applicant must not be blank");
            Objects.requireNonNull(panNumber, "panNumber is required");
            if (!panNumber.matches("[A-Z]{5}[0-9]{4}[A-Z]"))
                throw new IllegalArgumentException("panNumber must match [A-Z]{5}[0-9]{4}[A-Z]");
            Objects.requireNonNull(accountNumber, "accountNumber is required");
            if (income <= 0)          throw new IllegalArgumentException("income must be > 0");
            if (requestedAmount <= 0) throw new IllegalArgumentException("requestedAmount must be > 0");

            // Simple rules
            if (age != 0 && (age < 18 || age > 70))
                throw new IllegalArgumentException("age must be 18–70 when provided");
            if (tenureMonths < 6)
                throw new IllegalArgumentException("tenureMonths must be >= 6");

            // Cross-field rules
            if (isDefaulter)
                throw new IllegalArgumentException("defaulters are not eligible");
            if (existingEmi > income * 0.5)
                throw new IllegalArgumentException("existing EMI too high for income");

            return new LoanApplication(this);
        }
    }
}

// ============================================================
// PART C: SPLIT FACADES + ORCHESTRATOR
// ============================================================

// ---- Domain results (returned by facades) ----
class EligibilityResult {
    final boolean eligible;
    final String  reason;
    EligibilityResult(boolean e, String r) { this.eligible = e; this.reason = r; }
    boolean isEligible() { return eligible; }
}

class LoanQuote {
    final double amount, rate, emi;
    LoanQuote(double amount, double rate, double emi) {
        this.amount = amount; this.rate = rate; this.emi = emi;
    }
}

class ApprovalDecision {
    final boolean approved;
    final String  risk;
    ApprovalDecision(boolean a, String r) { this.approved = a; this.risk = r; }
    boolean isApproved() { return approved; }
}

// ---- Subsystems: Eligibility ----
class IncomeVerification     { boolean verifyIncome(double income)       { return income > 0; } }
class EmploymentVerification { boolean verifyEmployment(String employer) { return employer != null && !employer.isBlank(); } }
class KycVerification        { boolean verifyKyc(String pan, String aad){ return pan != null; } }
class FraudCheck             { boolean checkFraud(String pan)            { return false; /* false = not fraud */ } }

// ---- Subsystems: Pricing ----
class LoanAmountCalculator   { double calculate(double income)                { return income * 5; } }
class InterestRateCalculator { double calculate(int creditScore)             { return 8.5; } }
class EmiCalculator          { double calculate(double p, double r, int m)   { return m > 0 ? p / m : 0; } }

// ---- Subsystems: Approval ----
class RiskAssessmentEngine   { String assess(LoanApplication app)       { return "LOW"; } }
class ApprovalWorkflow       { boolean approve(String risk)             { return !"HIGH".equals(risk); } }
class CollateralValuation    { double valuate(String collateralType)    { return 500000; } }

// ---- Subsystems: Disbursement ----
class BankAccountVerifier    { boolean verify(String account)           { return account != null && !account.isBlank(); } }
class AgreementGenerator     { String generate(LoanApplication app)     { return "AGREEMENT-" + app.panNumber(); } }
class ESignService           { boolean sign(String agreement)           { return agreement != null; } }
class DisbursementService    { void disburse(String account, double amt){ System.out.println("   Disbursed Rs." + amt + " -> " + account); } }
class NotificationService    { void notify(String to, String msg)       { System.out.println("   Notified " + to + ": " + msg); } }

// ---- Subsystems: Compliance ----
class AuditLogger            { void log(String event)     { System.out.println("   AUDIT : " + event); } }
class RegulatoryReporter     { void report(String event)  { System.out.println("   REPORT: " + event); } }

// ============================================================
// FACADE 1 — Eligibility (6 deps, 1 public method)
// Used by: CustomerApp
// ============================================================
class LoanEligibilityFacade {
    private final CreditScoreCheck       creditScoreCheck;
    private final DocumentVerification   documentVerification;
    private final IncomeVerification     incomeVerification;
    private final EmploymentVerification employmentVerification;
    private final KycVerification        kycVerification;
    private final FraudCheck             fraudCheck;

    public LoanEligibilityFacade() {
        creditScoreCheck       = new CreditScoreCheck();
        documentVerification   = new DocumentVerification();
        incomeVerification     = new IncomeVerification();
        employmentVerification = new EmploymentVerification();
        kycVerification        = new KycVerification();
        fraudCheck             = new FraudCheck();
    }

    public EligibilityResult checkEligibility(LoanApplication app, List<String> docs) {
        boolean ok = creditScoreCheck.checkScore(app.panNumber())
                  && documentVerification.verifyDocuments(docs)
                  && incomeVerification.verifyIncome(app.income())
                  && employmentVerification.verifyEmployment(app.employer())
                  && kycVerification.verifyKyc(app.panNumber(), null)
                  && !fraudCheck.checkFraud(app.panNumber());   // !false = true
        return ok ? new EligibilityResult(true,  "All checks passed")
                  : new EligibilityResult(false, "One or more checks failed");
    }
}

// ============================================================
// FACADE 2 — Pricing (3 deps, 1 public method)
// Used by: CustomerApp
// ============================================================
class LoanPricingFacade {
    private final LoanAmountCalculator   amountCalc;
    private final InterestRateCalculator rateCalc;
    private final EmiCalculator          emiCalc;

    public LoanPricingFacade() {
        amountCalc = new LoanAmountCalculator();
        rateCalc   = new InterestRateCalculator();
        emiCalc    = new EmiCalculator();
    }

    public LoanQuote quote(LoanApplication app) {
        double amount = amountCalc.calculate(app.income());
        double rate   = rateCalc.calculate(750);
        double emi    = emiCalc.calculate(amount, rate, app.tenureMonths());
        return new LoanQuote(amount, rate, emi);
    }
}

// ============================================================
// FACADE 3 — Approval (3 deps, 1 public method)
// Used by: UnderwriterConsole
// ============================================================
class LoanApprovalFacade {
    private final RiskAssessmentEngine riskEngine;
    private final ApprovalWorkflow     workflow;
    private final CollateralValuation  collateral;

    public LoanApprovalFacade() {
        riskEngine = new RiskAssessmentEngine();
        workflow   = new ApprovalWorkflow();
        collateral = new CollateralValuation();
    }

    public ApprovalDecision approve(LoanApplication app) {
        String risk = riskEngine.assess(app);
        collateral.valuate(null);  // demo: no collateral info passed
        return new ApprovalDecision(workflow.approve(risk), risk);
    }
}

// ============================================================
// FACADE 4 — Disbursement (5 deps, 1 public method)
// Used by: FinanceConsole
// ============================================================
class LoanDisbursementFacade {
    private final BankAccountVerifier bankVerifier;
    private final AgreementGenerator  agreementGen;
    private final ESignService        eSign;
    private final DisbursementService disburser;
    private final NotificationService notifier;

    public LoanDisbursementFacade() {
        bankVerifier = new BankAccountVerifier();
        agreementGen = new AgreementGenerator();
        eSign        = new ESignService();
        disburser    = new DisbursementService();
        notifier     = new NotificationService();
    }

    public void disburse(LoanApplication app, double amount) {
        if (!bankVerifier.verify(app.accountNumber()))
            throw new IllegalStateException("Invalid bank account: " + app.accountNumber());
        String agreement = agreementGen.generate(app);
        if (!eSign.sign(agreement))
            throw new IllegalStateException("E-sign failed: " + agreement);
        disburser.disburse(app.accountNumber(), amount);
        notifier.notify(app.applicant(), "Loan disbursed: Rs." + amount);
    }
}

// ============================================================
// FACADE 5 — Compliance (2 deps, 1 public method)
// Used by: UnderwriterConsole, ComplianceDashboard
// ============================================================
class LoanComplianceFacade {
    private final AuditLogger        audit;
    private final RegulatoryReporter reporter;

    public LoanComplianceFacade() {
        audit    = new AuditLogger();
        reporter = new RegulatoryReporter();
    }

    public void record(String event) {
        audit.log(event);
        reporter.report(event);
    }
}

// ============================================================
// ORCHESTRATOR — SEQUENCING ONLY. NO business logic.
// ============================================================
class LoanApplicationOrchestrator {
    private final LoanEligibilityFacade  eligibility;
    private final LoanPricingFacade      pricing;
    private final LoanApprovalFacade     approval;
    private final LoanDisbursementFacade disbursement;
    private final LoanComplianceFacade   compliance;

    // Constructor injection — could be mocked in tests
    public LoanApplicationOrchestrator(LoanEligibilityFacade  eligibility,
                                       LoanPricingFacade      pricing,
                                       LoanApprovalFacade     approval,
                                       LoanDisbursementFacade disbursement,
                                       LoanComplianceFacade   compliance) {
        this.eligibility  = eligibility;
        this.pricing      = pricing;
        this.approval     = approval;
        this.disbursement = disbursement;
        this.compliance   = compliance;
    }

    public void placeApplication(LoanApplication app, List<String> docs) {
        System.out.println("\n--- Processing: " + app.applicant() + " ---");

        EligibilityResult e = eligibility.checkEligibility(app, docs);
        if (!e.isEligible()) {
            System.out.println("   Result: REJECTED (" + e.reason + ")");
            compliance.record("REJECTED: " + app.applicant());
            return;
        }
        System.out.println("   Eligible");

        LoanQuote q = pricing.quote(app);
        System.out.println("   Quote: Rs." + q.amount + " @ " + q.rate + "% EMI Rs." + q.emi);

        ApprovalDecision d = approval.approve(app);
        if (!d.isApproved()) {
            System.out.println("   Result: APPROVAL REJECTED (risk=" + d.risk + ")");
            compliance.record("APPROVAL_REJECTED: " + app.applicant());
            return;
        }
        System.out.println("   Approved (risk=" + d.risk + ")");

        disbursement.disburse(app, q.amount);
        compliance.record("DISBURSED: " + app.applicant());
    }
}

// ============================================================
// CLIENT CLASSES — each depends ONLY on what it needs
// ============================================================

// CustomerApp depends ONLY on eligibility + pricing.
// It has NO field/reference to LoanDisbursementFacade,
// ESignService, RegulatoryReporter, etc. That is the point.
class CustomerApp {
    private final LoanEligibilityFacade eligibility;
    private final LoanPricingFacade     pricing;

    public CustomerApp() {
        eligibility = new LoanEligibilityFacade();
        pricing     = new LoanPricingFacade();
    }

    public void checkOffer(LoanApplication app, List<String> docs) {
        EligibilityResult e = eligibility.checkEligibility(app, docs);
        System.out.println("CustomerApp -> eligible: " + e.isEligible());
        if (e.isEligible()) {
            LoanQuote q = pricing.quote(app);
            System.out.println("CustomerApp -> offer: Rs." + q.amount + " @ " + q.rate + "%");
        }
    }
}

// UnderwriterConsole depends ONLY on approval + compliance.
class UnderwriterConsole {
    private final LoanApprovalFacade   approval;
    private final LoanComplianceFacade compliance;

    public UnderwriterConsole() {
        approval   = new LoanApprovalFacade();
        compliance = new LoanComplianceFacade();
    }

    public void decide(LoanApplication app) {
        ApprovalDecision d = approval.approve(app);
        System.out.println("UnderwriterConsole -> approved: " + d.isApproved());
        compliance.record("UNDERWRITER_DECISION: " + app.applicant() + "=" + d.isApproved());
    }
}

// FinanceConsole depends ONLY on disbursement.
class FinanceConsole {
    private final LoanDisbursementFacade disbursement;

    public FinanceConsole() {
        disbursement = new LoanDisbursementFacade();
    }

    public void disburse(LoanApplication app, double amount) {
        System.out.println("FinanceConsole -> disbursing...");
        disbursement.disburse(app, amount);
    }
}

// ComplianceDashboard depends ONLY on compliance.
class ComplianceDashboard {
    private final LoanComplianceFacade compliance;

    public ComplianceDashboard() {
        compliance = new LoanComplianceFacade();
    }

    public void view(String event) {
        System.out.println("ComplianceDashboard -> recording: " + event);
        compliance.record(event);
    }
}

// ============================================================
// MAIN — runs all parts
// ============================================================
public class LoanFacadeAssignment {
    public static void main(String[] args) {

        System.out.println("========== PART A: Simple Facade ==========");
        new LoanCheckFacade().loanChecker();

        // ---------------------------------------------------
        System.out.println("\n========== PART B: Builder ==========");

        // Valid #1 — full
        LoanApplication alice = LoanApplication.builder()
                .applicant("Alice")
                .panNumber("ABCDE1234F")
                .accountNumber("ACC-001")
                .income(100000)
                .requestedAmount(500000)
                .tenureMonths(60)
                .employmentType("SALARIED")
                .city("Mumbai")
                .age(30)
                .employer("Acme Corp")
                .build();
        System.out.println("Valid #1: " + alice);

        // Valid #2 — minimal
        LoanApplication bob = LoanApplication.builder()
                .applicant("Bob")
                .panNumber("XYZAB5678C")
                .accountNumber("ACC-002")
                .income(80000)
                .requestedAmount(300000)
                .build();       // note: no employer set — will fail in eligibility later
        System.out.println("Valid #2: " + bob);

        // Invalid #1 — bad PAN
        try {
            LoanApplication.builder()
                    .applicant("Carol").panNumber("bad").accountNumber("ACC-003")
                    .income(50000).requestedAmount(100000).build();
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid #1 (bad PAN): " + ex.getMessage());
        }

        // Invalid #2 — defaulter
        try {
            LoanApplication.builder()
                    .applicant("Dave").panNumber("PQRST9999U").accountNumber("ACC-004")
                    .income(70000).requestedAmount(200000)
                    .isDefaulter(true).build();
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid #2 (defaulter): " + ex.getMessage());
        }

        // Invalid #3 — existing EMI too high (cross-field rule)
        try {
            LoanApplication.builder()
                    .applicant("Eve").panNumber("LMNOP1111Q").accountNumber("ACC-005")
                    .income(40000).requestedAmount(100000)
                    .existingEmi(25000).build();       // 25000 > 40000*0.5
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid #3 (EMI rule): " + ex.getMessage());
        }

        // ---------------------------------------------------
        System.out.println("\n========== PART C: Split Facades + Orchestrator ==========");
        List<String> docs = List.of("PAN", "AADHAAR", "SALARY_SLIP");

        LoanApplicationOrchestrator orchestrator = new LoanApplicationOrchestrator(
                new LoanEligibilityFacade(),
                new LoanPricingFacade(),
                new LoanApprovalFacade(),
                new LoanDisbursementFacade(),
                new LoanComplianceFacade()
        );

        orchestrator.placeApplication(alice, docs);   // full success
        orchestrator.placeApplication(bob,   docs);   // rejected — no employer

        // ---------------------------------------------------
        System.out.println("\n========== PART C5: Client demos (each uses its own facades) ==========");

        CustomerApp customer = new CustomerApp();
        customer.checkOffer(alice, docs);

        UnderwriterConsole underwriter = new UnderwriterConsole();
        underwriter.decide(alice);

        FinanceConsole finance = new FinanceConsole();
        finance.disburse(alice, 500000);

        ComplianceDashboard dashboard = new ComplianceDashboard();
        dashboard.view("AUDIT_VIEW: Alice");

        // ---------------------------------------------------
        // PROOF OF SPLIT (comment — will not compile if uncommented):
        // CustomerApp has no field/import for LoanDisbursementFacade.
        //   new CustomerApp().disbursement   // <-- "cannot find symbol"
        // The class that is NOT needed by CustomerApp is exactly the
        // class the split was meant to isolate. That is the payoff.
    }
}

// ============================================================
// PART D: Concept Quiz Answers
// ============================================================
/*
1. Is `LoanApplication app` a dependency or composition?
   -> DEPENDENCY. The facade receives it as a parameter, never stores it
      as a field, never creates it. When the method returns, no lingering
      reference. `LoanApplication` can exist and outlive the facade call
      completely independently.
      UML: LoanEligibilityFacade ..> LoanApplication (dashed arrow).

2. Is `private final CreditScoreCheck creditScoreCheck` a dependency,
   aggregation, or composition?
   -> In THIS code: COMPOSITION. The facade creates it inside its own
      constructor (`new CreditScoreCheck()`). When the facade is GC'd,
      no other reference exists -> the subsystem dies too. Filled diamond.

3. If we refactored to constructor injection, what changes?
   -> It becomes AGGREGATION.
        public LoanEligibilityFacade(CreditScoreCheck c) { this.c = c; }
      Now the CALLER creates and owns the subsystem. The subsystem can
      outlive the facade. Trade-off: loses auto-construction convenience;
      gains testability (inject a mock). This is the better default in
      production code.

4. The single most important question to detect a God Object facade?
   -> "Which CLIENTS use this facade, and do they call DISJOINT subsets
      of its methods?"
      If two clients call non-overlapping subsets, you have PROOF it is
      really two (or more) facades wearing one coat.
      Secondary checks: >5-7 dependencies, >5 public methods, cannot
      describe it in one sentence without "everything".

5. Why must the Orchestrator have NO business logic?
   -> Because the moment it contains domain rules, it becomes a NEW
      God Object. The whole point of splitting was to isolate business
      logic inside the small facades (each owning its own stage's rules).
      If the orchestrator starts checking "income > X" or computing
      "amount * rate", it re-centralizes exactly the logic we just
      distributed -- and coupling returns via the back door.
      The orchestrator's ONLY job is sequencing: call these in order,
      short-circuit on failure, never decide.
*/
