package block.code;

import merkle_root.code.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.security.NoSuchAlgorithmException;

public class App {
    public static void main(String[] args) throws InterruptedException {

        int argLength = args.length;
        if (argLength < 1) {
            System.err.println(
                    "Not enough arguments were provided. Please provide the filenames containing addresses and balances");
            System.exit(1);
        }

        LinkedList<Block> chain = new LinkedList<Block>();
        String prevHash = "0"; // For genesis block, will be updated on addition of blocks
        Boolean verbosity = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-v") || args[i].equals("--verbose")) {
                verbosity = true;
                continue;
            }

            ArrayList<Account> ledger = Input.parseFile(args[i]);
            String[] merkleRoot = Hash.getMerkleTree(ledger, ledger.size() - 1);

            Block block = new Block(prevHash, merkleRoot[0], Block.genNonce(), ledger);
            while (!block.checkNonce()) {
                // System.out.println("Try again");
                block.nonce = Block.genNonce();
            }
            // System.out.println("");

            // Thread.sleep(500); // Used to check if the time actually updates

            // Gets current block's hash for the next block
            try {
                prevHash = Encrypt.toHexString(Encrypt.getSHA(block.toString()));
            } catch (NoSuchAlgorithmException e) {
                System.err.println("ERROR: Unable to get SHA-256 hash");
                System.exit(1);
            }

            chain.addFirst(block);
        }
        String output = Chain.chainFormat(chain, verbosity);
        // System.out.println(output);
        Output.writeFile(args[0], output);
    }
}
