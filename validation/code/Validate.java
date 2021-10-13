package validation.code;

import merkle_root.code.*;
import block.code.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.*;

public class Validate {

    // Check if the input block's merkle root matches with any of those in the chain
    public boolean checkBlock(LinkedList<Block> chain, Block inputBlock) {

        ListIterator<Block> iterator = chain.listIterator();
        for (int i = 0; i < chain.size(); i++) {
            Block block = iterator.next();

            if (block.getMerkleRoot().compareTo(inputBlock.getMerkleRoot()) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkChain(LinkedList<Block> chain, LinkedList<Block> inputChain) {
        ListIterator<Block> iterator1 = chain.listIterator();
        ListIterator<Block> iterator2 = inputChain.listIterator();
        if (chain.size() != inputChain.size()) {
            return false;
        }

        for (int i = 0; i < chain.size(); i++) {
            Block block1 = iterator1.next();
            Block block2 = iterator2.next();
            if (block1.getMerkleRoot().compareTo(block2.getMerkleRoot()) == 0) {
                if (block1.getPrevHash().compareTo(block2.getPrevHash()) == 0) {
                    continue;
                } else {
                    return false;
                }
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
        return Arrays.asList(proof != null ? true : false, proof.get(0), proof.get(1));
    }

    public static List<Object> getProof(LinkedList<Block> blockchain, String address) {
        String balance = "";
        int index = 0;

        // Used to iterate through the blockchain
        ListIterator<Block> iterator = blockchain.listIterator();
        for (int i = 0; i < blockchain.size(); i++) {
            // Gets the account list from each block
            ArrayList<Account> accountList = iterator.next().getAccountList();
            // Iterates through the accountList to check if address in a member
            // TODO: Can modify so that the accountList is sorted in such a way so we can
            // more efficiently find address
            for (int j = 0; j < accountList.size(); j++) {
                Account acc = accountList.get(j);
                System.out.println(acc.getAddress());
                // If address matches, hold the address' balance and index of the address
                if (address.equals(acc.getAddress())) {
                    balance = acc.getBalance();
                    index = j;
                }
            }

            // Get membership proof
            if (!balance.isEmpty()) {
                // Only if balance was set in the previous for loop
                return Arrays.asList(balance, Hash.getMembershipProof(accountList, address, index));
            }
        }

        // If item does not exist at all in the blockchain, return null
        return null;
    }
}
