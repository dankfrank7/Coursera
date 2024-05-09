public class HelloGoodbye {
    public static void main(String[] args) {
        
        // Check if at least two arguments were passed
        if (args.length < 2) {
            System.out.println("Error: must pass at least 2 arguments");
        
        // If so print out hello and goodbye messages
        } else {
            String helloMessage = "Hello " + args[0] + " and " + args[1];
            String goodbyeMessage = "Goodbye " + args[0] + " and " + args[1];
            System.out.println(helloMessage);
            System.out.println(goodbyeMessage);
        }
    }
}