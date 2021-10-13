package validation.code;

import block.code.*;
import java.util.LinkedList;
import java.util.List;

public class App {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            System.err.println("Please provide the filename (*.block.out) and address of member");
            System.exit(1);
        }

        LinkedList<Block> blockchain = Input.parseFile(args[0]); // Load in the block
        String address = args[1]; // Address of the block

        List<Object> list = Validate.getBalance(blockchain, address);

        System.out.println(list);
    }
}
