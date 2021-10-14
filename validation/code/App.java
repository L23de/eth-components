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

        // LinkedList<Block> blockchain = new LinkedList<Block>();
        // LinkedList<Block> testchain2 = new LinkedList<Block>();
        // for (int i = 0; i < args.length; i++) {

        //     // Parse the block.out file generated from HW4
        //     blockchain = Input.parseBlockFile(args[i]);
        //     testchain2 = Input.parseBlockFile(args[i]);

        //     // Create arrays of accounts from ledger text files
        //     ArrayList<Account> ledger = merkle_root.code.Input.parseFile("block/files/ledger4.txt");
        //     ArrayList<Account> ledger2 = merkle_root.code.Input.parseFile("block/files/ledger1.txt");
        //     ArrayList<Account> ledger3 = merkle_root.code.Input.parseFile("block/files/ledger0.txt");
        //     ArrayList<Account> ledger4 = merkle_root.code.Input.parseFile("block/files/ledger2.txt");
        //     ArrayList<Account> ledger5 = merkle_root.code.Input.parseFile("block/files/ledger3.txt");
        //     ArrayList<Account> ledger6 = merkle_root.code.Input.parseFile("block/files/badBlock");

        //     // Get Merkle Roots
        //     String merkleRoot = Hash.getMerkleRoot(ledger, 0, ledger.size() - 1);
        //     String merkleRoot2 = Hash.getMerkleRoot(ledger2, 0, ledger2.size() - 1);
        //     String merkleRoot3 = Hash.getMerkleRoot(ledger3, 0, ledger3.size() - 1);
        //     String merkleRoot4 = Hash.getMerkleRoot(ledger4, 0, ledger4.size() - 1);
        //     String merkleRoot5 = Hash.getMerkleRoot(ledger5, 0, ledger5.size() - 1);
        //     String merkleRoot6 = Hash.getMerkleRoot(ledger6, 0, ledger6.size() - 1);

        //     String prevHash = "0", prevHash2 = "0", prevHash3 = "0", prevHash4 = "0", prevHash5 = "0";
        //     // Generate block from ledgers
        //     Block block = new Block(prevHash, merkleRoot, Block.genNonce(), ledger);

        //     // Gets current block's hash for the next block
        //     // Second Block
        //     try {
        //         prevHash2 = Encrypt.toHexString(Encrypt.getSHA(block.toString()));
        //     } catch (NoSuchAlgorithmException e) {
        //         System.err.println("ERROR: Unable to get SHA-256 hash");
        //         System.exit(1);
        //     }
        //     Block block2 = new Block(prevHash2, merkleRoot2, Block.genNonce(), ledger2);

        //     // Third Block
        //     try {
        //         prevHash3 = Encrypt.toHexString(Encrypt.getSHA(block2.toString()));
        //     } catch (NoSuchAlgorithmException e) {
        //         System.err.println("ERROR: Unable to get SHA-256 hash");
        //         System.exit(1);
        //     }
        //     Block block3 = new Block(prevHash3, merkleRoot3, Block.genNonce(), ledger3);

        //     // Fourth Block
        //     try {
        //         prevHash4 = Encrypt.toHexString(Encrypt.getSHA(block3.toString()));
        //     } catch (NoSuchAlgorithmException e) {
        //         System.err.println("ERROR: Unable to get SHA-256 hash");
        //         System.exit(1);
        //     }
        //     Block block4 = new Block(prevHash4, merkleRoot4, Block.genNonce(), ledger4);

        //     // Fifth Block
        //     try {
        //         prevHash5 = Encrypt.toHexString(Encrypt.getSHA(block4.toString()));
        //     } catch (NoSuchAlgorithmException e) {
        //         System.err.println("ERROR: Unable to get SHA-256 hash");
        //         System.exit(1);
        //     }
        //     Block block5 = new Block(prevHash5, merkleRoot5, Block.genNonce(), ledger5);

        //     // Generate a bad block
        //     Block badBlock = new Block(prevHash, merkleRoot6, Block.genNonce(), ledger6);

        //     // Create a test blockchain with the blocks from above
        //     LinkedList<Block> testChain = new LinkedList<Block>();
        //     testChain.push(block5);
        //     testChain.push(block4);
        //     testChain.push(block3);
        //     testChain.push(block2);
        //     testChain.push(block);

        //     // Check if the block is good or bad based on whether the block exists in the
        //     // blockchain
        //     System.out.println("Check bad block is " + Validate.checkBlock(blockchain, badBlock));
        //     System.out.println("Check good block is " + Validate.checkBlock(blockchain, block2));

        //     // Check the blockchain based on their Merkle root and previous hash
        //     System.out.println("Check bad blockhain is " + Validate.checkChain(blockchain, testChain));
        //     System.out.println("Check good blockchain is " + Validate.checkChain(blockchain, testchain2));

        //     // for (int j = 0; j < testChain.size(); j++) {
        //     // System.out.println("Blockchain Block at " + j + " is \n" +
        //     // blockchain.get(j));
        //     // System.out.println("TEST chain Block at " + j + " is \n" + testChain.get(j));
        //     // System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        //     // }
        // }

        LinkedList<Block> blockchain = Input.parseBlockFile(args[0]); // Load in the block
        String address = args[1]; // Address of the block

        System.out.println(blockchain.toString());

        List<Object> list = Validate.getBalance(blockchain, address);

        // Prints in a more neater (But still not very neat format)
        System.out.println(list.get(0)); // Boolean
        System.out.println(list.get(1)); // Balance (int)
        System.out.println(list.get(2)); // Proof (ArrayList<String>)
        System.out.println(list.get(3)); // Headers (ArrayList<Hashtable<String, String>>)

        System.out.println("Address: " + address);
    }
}
