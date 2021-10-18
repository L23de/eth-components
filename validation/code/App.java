package validation.code;

import block.code.*;
import merkle_root.code.*;
import java.util.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;

public class App {
    public static void main(String[] args) throws InterruptedException {
        LinkedList<Block> blockchain = new LinkedList<Block>();
        LinkedList<Block> testchain = new LinkedList<Block>();

        if (args.length == 2) { // Address validation
            blockchain = Input.parseBlockFile(args[0]); // Load in the block
            String address = args[1]; // Address of the block

            List<Object> list = Validate.getBalance(blockchain, address);

            // Prints in a more neater (But still not very neat format)
            // Unable to use the Gson package to pretty-print output, use
            // http://jsonviewer.stack.hu/ to do so
            System.out.println("Address Validation: \n");
            System.out.println("Found: " + list.get(0)); // Boolean
            System.out.println("\nProof of Membership:\n" + list.get(2)); // Proof (ArrayList<String>)
            System.out.println("\nHeaders:\n" + list.get(3)); // Headers (ArrayList<Hashtable<String, String>>)
            System.out.println("\nBalance: " + list.get(1)); // Balance (int)
            System.out.println("\nAddress: " + address);
        } else if (args.length == 1) {

            // Parse the block.out file generated from HW4
            blockchain = Input.parseBlockFile(args[0]);

            validateDriver(blockchain, testchain);
        }
    }

    static void validateDriver(LinkedList<Block> blockchain, LinkedList<Block> testchain) {
        // Create arrays of accounts from ledger text files
        ArrayList<Account> ledger = merkle_root.code.Input.parseFile("block/files/ledger0.txt");
        ArrayList<Account> ledger2 = merkle_root.code.Input.parseFile("block/files/ledger1.txt");
        ArrayList<Account> ledger3 = merkle_root.code.Input.parseFile("block/files/ledger2.txt");
        ArrayList<Account> ledger4 = merkle_root.code.Input.parseFile("block/files/ledger3.txt");
        ArrayList<Account> ledger5 = merkle_root.code.Input.parseFile("block/files/ledger4.txt");
        // Bad block is just a previously-generated block
        ArrayList<Account> badLedger = merkle_root.code.Input.parseFile("block/files/badBlock");

        // Get Merkle Roots
        String merkleRoot = Hash.getMerkleTree(ledger, ledger.size() - 1)[0];
        String merkleRoot2 = Hash.getMerkleTree(ledger2, ledger2.size() - 1)[0];
        String merkleRoot3 = Hash.getMerkleTree(ledger3, ledger3.size() - 1)[0];
        String merkleRoot4 = Hash.getMerkleTree(ledger4, ledger4.size() - 1)[0];
        String merkleRoot5 = Hash.getMerkleTree(ledger5, ledger5.size() - 1)[0];
        String merkleRoot6 = Hash.getMerkleTree(badLedger, badLedger.size() - 1)[0];

        String prevHash = "0";
        Block block1, block2, block3, block4, block5;

        block1 = new Block(prevHash, merkleRoot, Block.genNonce(), ledger);
        // prevHash = Encrypt.toHexString(Encrypt.getSHA(block1.toString()));
        // block2 = new Block(prevHash, merkleRoot2, Block.genNonce(), ledger2);
        // prevHash = Encrypt.toHexString(Encrypt.getSHA(block2.toString()));
        // block3 = new Block(prevHash, merkleRoot3, Block.genNonce(), ledger3);
        // prevHash = Encrypt.toHexString(Encrypt.getSHA(block3.toString()));
        // block4 = new Block(prevHash, merkleRoot4, Block.genNonce(), ledger4);
        // prevHash = Encrypt.toHexString(Encrypt.getSHA(block4.toString()));
        // block5 = new Block(prevHash, merkleRoot5, Block.genNonce(), ledger5);

        // Generate a bad block
        Block badBlock = new Block("0", merkleRoot6, Block.genNonce(), badLedger);

        System.out.println("Component Validation:\n");

        // Check if the block is good or bad based on whether the block exists in the
        // blockchain
        System.out.println(
                "Bad Block Validation: " + (Validate.checkBlock(blockchain, badBlock) ? "Success" : "Failure"));
        System.out
                .println("Good Block Validation: " + (Validate.checkBlock(blockchain, block1) ? "Success" : "Failure"));

        // Create a test blockchain with the blocks from above
        // testChain.push(block5);
        // testChain.push(block4);
        // testChain.push(block3);
        // testChain.push(block2);
        testchain.push(block1);

        // Check the blockchain based on their Merkle root and previous hash
        System.out.println();
        System.out.println(
                "Bad Chain Validation: " + (Validate.checkChain(blockchain, testchain) ? "Success" : "Failure"));
        System.out.println(
                "Good Chain Validation: " + (Validate.checkChain(blockchain, blockchain) ? "Success" : "Failure"));
    }
}
