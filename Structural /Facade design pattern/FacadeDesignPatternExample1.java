
class Order{
    void orderservice(){System.out.println("order placed !!!");}
}

class Payment{
    void paymentservice(){System.out.println("payment placed !!!");}
}

class Distribution{
    void distributionservice(){System.out.println("Distribution-shipping is  placed !!!");}
}


class orderFacade{
    private Order order;
    private Payment payment;
    private Distribution distribution;
    
    orderFacade(){
        order = new Order();
        payment= new Payment();
        distribution= new Distribution();
    }

    void place_full_order(){
        order.orderservice();
        payment.paymentservice();
        distribution.distributionservice();

        System.out.println("------Your full order is placed via FACADE---------");
        
    }
    
}
public class Main {
    public static void main(String[] args) {

    orderFacade  orderfacade_object =new  orderFacade();
    orderfacade_object.place_full_order();
    System.out.println("------Exiting the Code successfully---------");


    
        
    }
}
