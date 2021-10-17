package validation.code;

import java.util.LinkedList;
import java.util.List;

import block.code.Block;

public class App {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            System.err.println("Please provide the filename (*.block.out) and address of member");
            System.exit(1);
        }

        LinkedList<Block> blockchain = Input.parseBlockFile(args[0]); // Load in the block
        String address = args[1]; // Address of the block

        List<Object> list = Validate.getBalance(blockchain, address);

        // Prints in a more neater (But still not very neat format)
        // Unable to use the Gson package to pretty-print output, use
        // http://jsonviewer.stack.hu/ to do so
        System.out.println("Found: " + list.get(0)); // Boolean
        System.out.println("\nProof of Membership:\n" + list.get(2)); // Proof (ArrayList<String>)
        System.out.println("\nHeaders:\n" + list.get(3)); // Headers (ArrayList<Hashtable<String, String>>)
        System.out.println("\nBalance: " + list.get(1)); // Balance (int)
        System.out.println("\nAddress: " + address);
    }
}
