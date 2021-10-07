package merkle_root.code;

import block.code.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Computes the Merkle root given a array of leaf nodes (assume sorted) Similar
 * to a merge sort in terms of divide and conquer
 */
public class Hash {
    public static String getMerkleRoot(ArrayList<Account> ledger, int start, int end) {
        // If recursion reaches a leaf, return an empty string
        if (start == end) {

            if (ledger.get(start).address == null) {
                return "";
            }
            return ledger.get(start).hash;
        }

        int mid = (start + end) / 2;

        // Gets the left and right hashes of the tree
        String leftHash = getMerkleRoot(ledger, start, mid);
        String rightHash = getMerkleRoot(ledger, mid + 1, end);
        String concatHash = leftHash + rightHash;

        // If both left and right hashes are empty (Hash attempt of padded values),
        // return nothing, since the hash of the empty string "" is still valid and it
        // would modify the final resulting hash
        if (concatHash == "") {
            return "";
        }

        // Get the SHA-256 hash of the leaf
        try {
            return Encrypt.toHexString(Encrypt.getSHA(concatHash));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("ERROR: Unable to get SHA-256 hash");
            System.exit(1);
        }
        return "";
    }

    public static ArrayList<String> getMembershipProof(ArrayList<Account> accList) {
        ArrayList<String> proof;
        return proof;
    }
}
