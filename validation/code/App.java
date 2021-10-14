package validation.code;

import block.code.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            System.err.println("Please provide the filename (*.block.out) and address of member");
            System.exit(1);
        }

        LinkedList<Block> blockchain = Input.parseFile(args[0]); // Load in the block
        String address = args[1]; // Address of the block

        List<Object> list = Validate.getBalance(blockchain, address);

        System.out.println(list.get(0)); // Boolean
        System.out.println(list.get(1)); // Balance (int)
        System.out.println(list.get(2)); // Proof (ArrayList<String>)
        System.out.println(list.get(3)); // Headers (ArrayList<Hashtable<String, String>>)
    }
}
