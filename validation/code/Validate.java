package validation.code;

import merkle_root.code.*;
import block.code.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.*;

public class Validate {

    // Check if the input block's merkle root matches with any of those in the chain
    public static boolean checkBlock(LinkedList<Block> chain, Block inputBlock) {

        ListIterator<Block> iterator = chain.listIterator();
        for (int i = 0; i < chain.size(); i++) {
            Block block = iterator.next();

            if (block.getMerkleRoot().compareTo(inputBlock.getMerkleRoot()) == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkChain(LinkedList<Block> chain) {
        // ListIterator<Block> iterator1 = chain.listIterator();
        // ListIterator<Block> iterator2 = inputChain.listIterator();
        // if (chain.size() != inputChain.size()) {
        // return false;
        // }

        // for (int i = 0; i < chain.size(); i++) {
        // Block block1 = iterator1.next();
        // Block block2 = iterator2.next();
        // if (block1.getMerkleRoot().compareTo(block2.getMerkleRoot()) == 0) {
        // if (block1.getPrevHash().compareTo(block2.getPrevHash()) == 0) {
        // continue;
        // } else {
        // return false;
        // }
        // } else {
        // return false;
        // }
        // }

        // return true;
        ListIterator<Block> iterator = chain.listIterator();
        String currHash = "0";

        // Loop through every block in the blockchain
        for (int i = 0; i < chain.size(); i++) {
            // Iterate each block
            Block currBlock = iterator.next();

            System.out.println("Curr Hash is " + currHash);
            System.out.println("Prev Hash is " + currBlock.getPrevHash());
            System.out.println("Curr block nonce " + currBlock.getNonce());
            System.out.println(currBlock.toString());
            System.out.println("--------------------------------------");

            // Check if current block's previous hash equal to the previous block's hash
            if (currHash.equals(currBlock.getPrevHash())) {
                // Get the hash for the current block
                currHash = Hash.hashObj(currBlock.toString());
                System.out.println(currHash);
                System.out.println(Hash.hashObj(currBlock.toString()));
                System.out.println(Hash.hashObj(currBlock.toString()));
                System.out.println(Hash.hashObj(currBlock.toString()));
                System.out.println(Hash.hashObj(currBlock.toString()));
                System.out.println(Hash.hashObj(currBlock.toString()));

                continue;
            } else {
                return false;
            }
        }

        return true;
    }

    public static List<Object> getBalance(LinkedList<Block> blockchain, String address) {
        // proof will contain both the balance of the address and the proof of
        // membership
        List<Object> proof = getProof(blockchain, address);
        if (proof == null) {
            return Arrays.asList(false, null, null, null);
        }
        return Arrays.asList(true, proof.get(0), proof.get(1), proof.get(2));
    }

    public static <T> List<Object> getProof(LinkedList<Block> blockchain, String address) {
        Block block;
        String balance = "";
        int index = 0;

        // Used to iterate through the blockchain
        ListIterator<Block> iterator = blockchain.listIterator();
        for (int i = 0; i < blockchain.size(); i++) {
            // Gets the account list from each block
            block = iterator.next();
            ArrayList<Account> accountList = block.getAccountList();
            // Iterates through the accountList to check if address in a member
            // TODO: Can modify so that the accountList is sorted in such a way so we can
            // more efficiently find address
            for (int j = 0; j < accountList.size(); j++) {
                Account acc = accountList.get(j);
                // System.out.println(acc.getAddress());
                // If address matches, hold the address' balance and index of the address
                if (address.equals(acc.getAddress())) {
                    balance = acc.getBalance();
                    index = j;
                    break;
                }
            }

            // Get membership proof
            if (!balance.isEmpty()) {
                ArrayList<Hashtable<String, String>> headers = new ArrayList<Hashtable<String, String>>();

                while (true) { // Iterates to the most current (beginning of the ArrayList)

                    Hashtable<String, String> header = new Hashtable<>();
                    header.put("prevHash", block.getPrevHash());
                    header.put("merkleRoot", block.getMerkleRoot());
                    header.put("timestamp", block.getTimestamp());
                    header.put("diffTarget", block.getDiffTarget());
                    header.put("nonce", block.getNonce());
                    if (iterator.hasNext()) {
                        block = iterator.next();
                        header.put("hash", block.getPrevHash());
                    } else { // Break out of the infinite loop when reaches the head of the ArrayList
                        header.put("hash", Hash.hashObj(block.toString()));
                        headers.add(header);
                        break;
                    }
                    headers.add(header);
                }

                // Only if balance was set in the previous for loop
                return Arrays.asList(balance, Hash.getMembershipProof(accountList, address, index), headers);
            }
        }

        // If item does not exist at all in the blockchain, return null
        return null;
    }

}
