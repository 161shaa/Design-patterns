//This will be implementation -LLP Lower level part/Layer
interface Laptop {
    void useLaptop();
}
class DellLaptop implements Laptop {
    @Override
    public void useLaptop() {
        System.out.println("Dell Laptop");
    }
}
class HPLaptop implements Laptop {
    @Override
    public void useLaptop() {
        System.out.println("HP Laptop");
    }
}
class MacBook implements Laptop {
    @Override
    public void useLaptop() {
        System.out.println("MacBook");
    }
}






//Below will be abstraction -HLP Higher level part/layer
abstract class Employee {
    protected Laptop laptop;
    Employee(Laptop laptop) {
        this.laptop = laptop;
    }
    abstract void work();
}
class Developer extends Employee {
    Developer(Laptop laptop) {
        super(laptop);
    }
    @Override
    void work() {
        System.out.print("Developer working using ");
        laptop.useLaptop();
    }
}
class Tester extends Employee {
    Tester(Laptop laptop) {
        super(laptop);
    }
    @Override
    void work() {
        System.out.print("Tester working using ");
        laptop.useLaptop();
    }
}
class DevOpsEngineer extends Employee {
    DevOpsEngineer(Laptop laptop) {
        super(laptop);
    }
    @Override
    void work() {
        System.out.print("DevOps Engineer working using ");
        laptop.useLaptop();
    }
}




public class Main {
    public static void main(String[] args) {

      //clinet which will interact
        Employee dev =
                new Developer(new MacBook());
        Employee tester =
                new Tester(new HPLaptop());
        Employee devops =
                new DevOpsEngineer(new DellLaptop());
        dev.work();
        tester.work();
        devops.work();
    }
}
