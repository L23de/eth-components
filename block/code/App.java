package block.code;

import merkle_root.code.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class App {
    public static void main(String[] args) {
        int argLength = args.length;
        if (argLength < 1) {
            System.err.println(
                    "Not enough arguments were provided. Please provide the filenames containing addresses and balances");
            System.exit(1);
        }

        LinkedList<Block> chain = new LinkedList<Block>();
        String prevHash = "0"; // For genesis block, will be updated on addition of blocks

        for (int i = 0; i <= argLength; i++) {
            ArrayList<Account> ledger = Input.parseFile(args[i]);
            String merkleRoot = Hash.getMerkleRoot(ledger, 0, ledger.size() - 1);
            // TODO: Set prevHash to previous block's header's hash
            chain.addFirst(new Block(prevHash, merkleRoot));
        }
    }
}
