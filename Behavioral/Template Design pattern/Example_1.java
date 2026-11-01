// 1. The Abstract Class
abstract class PaymentProcedure {

    // Abstract steps (Subclasses MUST implement these)
    public abstract void initiatePayment();
    public abstract void makePayment();
    public abstract void generateReceipt();

    // Concrete step (Common to all subclasses)
    public void authenticate() {
        System.out.println("Verified by Google Authentication");
    }

    // THE TEMPLATE METHOD
    // 'final' ensures the algorithm's structure cannot be changed
    public final void processPayment() {
        initiatePayment();
        authenticate();      // Hook step
        makePayment();
        generateReceipt();
    }
}

// 2. Concrete Subclass 1
class UPIPayment extends PaymentProcedure {
    public void initiatePayment() { System.out.println("UPI payment started"); }
    public void makePayment() { System.out.println("Your UPI payment is done - Thank you!"); }
    public void generateReceipt() { System.out.println("UPI Receipt generated at XX:XX:XX"); }
}

// 3. Concrete Subclass 2
class BankPayment extends PaymentProcedure {
    public void initiatePayment() { System.out.println("Bank payment started"); }
    public void makePayment() { System.out.println("Your Bank payment is done - Thank you!"); }
    public void generateReceipt() { System.out.println("Bank Receipt generated at XX:XX:XX"); }
}

// 4. The Client
public class Main {
    public static void main(String[] args) {
        
        PaymentProcedure upi = new UPIPayment();
        upi.processPayment();

        System.out.println("----------------------------------");

        PaymentProcedure bank = new BankPayment();
        bank.processPayment();
    }
}
