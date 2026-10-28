//third party library-which is Adaptee
class ReviewDetails{
    void ReviewByManager(){
        System.out.println("reviewed by SSIS manager");
    }
}

//interface that will interact with the adaptee
interface Reviewer{
    void Review();
}

//Adapter class- done through the means of class adapter rather than object adapter
class ReviewAdapaterByVendorManager 
    extends ReviewDetails
    implements Reviewer{

        public  void Review(){
            super.ReviewByManager();
        }
    }

public class Main {
    public static void main(String[] args) {

        
ReviewAdapaterByVendorManager ReviewObject = new ReviewAdapaterByVendorManager();
    ReviewObject.Review();
        
        
    }
}
