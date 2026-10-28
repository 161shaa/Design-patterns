// 1. The Complex Subsystems
class CPU {
    public void freeze() { System.out.println("CPU freezing..."); }
    public void jump(long position) { System.out.println("CPU jumping to " + position); }
    public void execute() { System.out.println("CPU executing..."); }
}

class Memory {
    public void load(long position, byte[] data) { System.out.println("Memory loading data..."); }
}

class HardDrive {
    public byte[] read(long lba, int size) { return new byte[size]; }
}




// 2. THE FACADE
class ComputerFacade {
  
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    // One simple method hides all the complexity
    public void startComputer() {
        System.out.println("Starting computer...");
        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump(0);
        cpu.execute();
    }
}


// 3. The Client
public class Main {

    public static void main(String[] args) {
      
        ComputerFacade computer = new ComputerFacade();
        computer.startComputer();
    }
}
