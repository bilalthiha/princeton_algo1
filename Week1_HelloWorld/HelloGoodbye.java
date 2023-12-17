public class HelloGoodbye {
    public static void main(String[] args) {
        // strictly takes in 2 input arguments from command line
        if (args.length == 2) {
            System.out.println("Hello " + args[0] + " and " + args[1] + ".");
            System.out.println("Goodbye " + args[1] + " and " + args[0] + ".");
        }
        else {
            System.out.println(
                    "Invalid input. Please input 2 command line arguments and try again.");
        }
    }
}
