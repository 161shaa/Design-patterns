
interface Camera {
    void analyze();
}

// 1. Security Operator
class SecurityOperator {

    private String name;
    private String role;

    public SecurityOperator(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}

// 2. Real Subject
class RealCameraAI implements Camera {

    private int cameraId;

    public RealCameraAI(int cameraId) {
        this.cameraId = cameraId;

        System.out.println("Loading AI model...");
        System.out.println("Connecting to camera " + cameraId);
    }

    @Override
    public void analyze() {
        System.out.println("Analyzing camera " + cameraId + " footage...");
        System.out.println("Suspicious activity detection completed!");
    }
}

// 3. Proxy
class CameraProxy implements Camera {

    private int cameraId;
    private RealCameraAI realCamera;
    private SecurityOperator operator;

    public CameraProxy(int cameraId, SecurityOperator operator) {
        this.cameraId = cameraId;
        this.operator = operator;
    }

    // Permission checking
    private boolean checkPermission() {

        String role = operator.getRole();

        return role.equalsIgnoreCase("ADMIN")
            || role.equalsIgnoreCase("SECURITY");
    }

    @Override
    public void analyze() {

        System.out.println("\nOperator: " + operator.getName()
                + " (" + operator.getRole() + ")");

        System.out.println("Camera " + cameraId + ": Access requested");

        // Protection Proxy
        if (!checkPermission()) {
            System.out.println("ACCESS DENIED!");
            return;
        }

        System.out.println("Access granted!");

        // Virtual Proxy - Lazy Initialization
        if (realCamera == null) {
            realCamera = new RealCameraAI(cameraId);
        }

        // Delegate to Real Subject
        realCamera.analyze();
    }
}

// 4. Client
public class Main {

    public static void main(String[] args) {

        SecurityOperator rahul =
            new SecurityOperator("Rahul", "VIEWER");

        SecurityOperator priya =
            new SecurityOperator("Priya", "SECURITY");

        SecurityOperator admin =
            new SecurityOperator("Arun", "ADMIN");

        // Create proxies
        Camera rahulCamera = new CameraProxy(101, rahul);

        Camera priyaCamera = new CameraProxy(101, priya);

        Camera adminCamera = new CameraProxy(102, admin);

        System.out.println("===== SMART CITY SURVEILLANCE SYSTEM =====");

        // Unauthorized access
        rahulCamera.analyze();

        // Authorized access
        priyaCamera.analyze();

        // Same proxy - AI engine reused
        priyaCamera.analyze();

        // Another authorized operator and camera
        adminCamera.analyze();
    }
}
