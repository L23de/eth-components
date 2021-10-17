package validation.code;

import block.code.*;
import merkle_root.code.*;
import validation.code.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

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
        System.out.println("Found: " + list.get(0)); // Boolean
        System.out.println("\nProof of Membership:\n" + list.get(2)); // Proof (ArrayList<String>)
        System.out.println("\nHeaders:\n" + list.get(3)); // Headers (ArrayList<Hashtable<String, String>>)
        System.out.println("\nBalance: " + list.get(1)); // Balance (int)
        System.out.println("\nAddress: " + address);
    }
}
