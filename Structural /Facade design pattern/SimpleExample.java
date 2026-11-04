class CreditScoreCheck{
    boolean checkScore(){return true ;}
}

class DocumentVerification{
    boolean verifyDocuments(){return true;}
}

class LoanStatus{
    
    void status()
    {
        
    System.out.println("---------------------------------");
    System.out.println("Loan Approved");
    System.out.println("---------------------------------");
    
    }
}


class LoanCheckFacade{
    private  CreditScoreCheck creditScoreCheck;
    private DocumentVerification documentVerification;
    private LoanStatus loanStatus;

    public LoanCheckFacade(){
        
        this.creditScoreCheck = new  CreditScoreCheck();
        this.documentVerification = new  DocumentVerification();
        this.loanStatus = new  LoanStatus();
        
    }

    void loanChecker(){

        if(this.creditScoreCheck.checkScore() && this.documentVerification.verifyDocuments()){
            this.loanStatus.status();
        }else{
            System.out.println("---------------------------------");
            System.out.println("Loan Rejected");
            System.out.println("---------------------------------");
        }
    }

    
    
}
public class Main {
    public static void main(String[] args) {

        LoanCheckFacade loan= new LoanCheckFacade();
        loan.loanChecker();
        
    }
}
