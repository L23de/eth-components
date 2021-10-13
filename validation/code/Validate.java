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


    public static boolean checkChain(LinkedList<Block> chain, LinkedList<Block> inputChain) {
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
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }

        return true;
    }

    // public static List<Object> getBalance(LinkedList<Block> blockchain, String address) {
    //     // proof will contain both the balance of the address and the proof of membership
    //     List<Object> proof = getProof(blockchain, address);
    //     return Arrays.asList(proof != null ? true : false, proof);
    // }

    // public static List<Object> getProof(LinkedList<Block> blockchain, String address) {
    //     ArrayList<String> proof;
    //     String balance = "";

    //     // Used to iterate through the blockchain
    //     ListIterator<Block> iterator = blockchain.listIterator();
    //     for (int i = 0; i < blockchain.size(); i++) {
    //         // Gets the account list from each block
    //         ArrayList<Account> accountList = iterator.next().getAccountList();
    //         // Iterates through the accountList to check if address in a member
    //         // TODO: Can modify so that the accountList is sorted in such a way so we can more efficiently find address
    //         for (Account acc: accountList) {
    //             // If address matches, hold the address' balance
    //             if (address == acc.getAddress()) {
    //                 balance = acc.getBalance();
    //                 break;
    //             }
    //         }
    //         if (balance.isEmpty()) {
    //             // Only if balance was set in the previous for loop
    //             proof = Hash.getMembershipProof(accountList);
    //             return Arrays.asList(balance, proof);
    //         }
    //     }

    //     // If item does not exist at all in the blockchain, return null
    //     return null;
    // }

}
