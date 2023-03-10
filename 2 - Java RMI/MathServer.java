import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.lang.SecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class MathServer extends UnicastRemoteObject implements MathService {

    private int clients = 0;

    public MathServer() throws RemoteException {
        super();
    }

    public synchronized int incrementClientCount() {
        return ++clients;
    }

    public int add(int a, int b) throws RemoteException {
        System.out.println("Adding " + a + " and " + b + " in the Server");
        return a + b;
    }

    public int subtract(int a, int b) throws RemoteException {
        System.out.println("Substracting " + a + " and " + b + " in the Server");
        return a - b;
    }

    public int multiply(int a, int b) throws RemoteException {
        System.out.println("Multiplying " + a + " and " + b + " in the Server");
        return a * b;
    }

    // this method is not remotely accessible as it's not in the remote interface
    public int test(int a) {
        System.out.println("this is a test");
        return 0;
    }

    public int divide(int a, int b) throws RemoteException {

        System.out.println("Dividing " + a + " and " + b + " in the Server");

        for (double i = 0; i < 10000000000000000.0; i++) {
            System.out.println("I'm doing something that takes a long time.");
        }

        return a / b; // check for division with zero here!
    }

    public static void main(String[] args) {

        // set the policy file as the system securuty policy
        System.setProperty("java.security.policy", "file:allowall.policy");

        try {

            MathServer svr = new MathServer();
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("CalculatorService", svr);

            System.out.println("Service started....");
        } catch (RemoteException re) {
            System.err.println(re.getMessage());
        } catch (AlreadyBoundException abe) {
            System.err.println(abe.getMessage());
        }
    }
}