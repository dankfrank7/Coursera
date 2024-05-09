package Introduction;
public class HelloGoodbye {
    public static void main(String[] args) {
        // Ensure exactly two arguments are passed
        if (args.length != 2) {
            System.err.println("Error: Exactly two arguments required.");
        } else {
            // Print hello and goodbye messages directly
            System.out.println("Hello " + args[0] + " and " + args[1] + ".");
            System.out.println("Goodbye " + args[0] + " and " + args[1] + ".");
        }
    }
}