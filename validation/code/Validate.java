package validation.code;

import merkle_root.code.*;
import block.code.*;

import java.util.*;

public class Validate {

    public static List<Object> getBalance(LinkedList<Block> blockchain, String address) {
        // proof will contain both the balance of the address and the proof of membership
        List<Object> proof = getProof(blockchain, address);
        return Arrays.asList(proof != null ? true : false, proof);
    }

    public static List<Object> getProof(LinkedList<Block> blockchain, String address) {
        ArrayList<String> proof;
        String balance = "";

        // Used to iterate through the blockchain
        ListIterator<Block> iterator = blockchain.listIterator();
        for (int i = 0; i < blockchain.size(); i++) {
            // Gets the account list from each block
            ArrayList<Account> accountList = iterator.next().getAccountList();
            // Iterates through the accountList to check if address in a member
            // TODO: Can modify so that the accountList is sorted in such a way so we can more efficiently find address
            for (Account acc: accountList) {
                // If address matches, hold the address' balance
                if (address == acc.getAddress()) {
                    balance = acc.getBalance();
                    break;
                }
            }
            if (balance.isEmpty()) {
                // Only if balance was set in the previous for loop
                proof = Hash.getMembershipProof(accountList);
                return Arrays.asList(balance, proof);
            }
        }

        // If item does not exist at all in the blockchain, return null
        return null;
    }
}
