// 1. The Interface
interface RealData {
    void display();
}





// 2. The Real Subjects (Heavy objects)
class RealImageDB implements RealData {

    public RealImageDB() {
        // Imagine this takes 5 seconds to load a huge BLOB from the DB
        System.out.println("Loading massive IMAGE from database...");
    }

    public void display() { System.out.println("Displaying IMAGE."); }
}




class RealVideoDB implements RealData {
    public RealVideoDB() {
        // Imagine this takes 10 seconds to load
        System.out.println("Loading massive VIDEO from database...");
    }
    public void display() { System.out.println("Displaying VIDEO."); }
}








// 3. THE PROXY
class DataProxy implements RealData {


    private RealData realDataRef; // It starts as null
    private String type;

    public DataProxy(String type) {
        this.type = type;
    }



    @Override
    public void display() {
        System.out.println("Proxy: Validating & Authenticating...");

        // LAZY LOADING: The Proxy creates the object ONLY when needed
        if (realDataRef == null) {
            if (type.equalsIgnoreCase("image")) {
                realDataRef = new RealImageDB(); // Created HERE, not in Main!
            } else if (type.equalsIgnoreCase("video")) {
                realDataRef = new RealVideoDB();
            }
        }



        // Now it calls the method
        realDataRef.display();
    }
}









// 4. The Client
public class Main {

    public static void main(String[] args) {


        // ✅ The Client ONLY creates the Proxy.
        // It does NOT know RealImageDB or RealVideoDB exist!


        RealData proxy1 = new DataProxy("image");

        System.out.println("--- App started, but nothing loaded yet ---");


        // The heavy object is created and displayed ONLY when we call this
        proxy1.display();
    }
}
