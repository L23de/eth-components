package merkle_root.code;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Computes the Merkle root given a array of leaf nodes (assume sorted) Similar
 * to a merge sort in terms of divide and conquer
 */
public class Hash {
    public static String[] getMerkleTree(ArrayList<Account> ledger, int size) {
        String[] merkleTree = new String[2 * size + 1];
        // Fill up the leaves
        for (int i = 0; i <= size; i++) {
            // Leaves are [size...2 * size]
            // size is used as an offset
            String hash = ledger.get(i).hash;
            if (hash == null) { // In cases where there is a padded account
                merkleTree[size + i] = "";
            } else {
                merkleTree[size + i] = hash;
            }
        }

        // Fill up the rest of the tree w/ hashes
        // Condition is when merkleRoot is not found (At index 0)
        for (int i = size - 1; i >= 0; i--) {
            String leftHash = merkleTree[i * 2 + 1];
            String rightHash = merkleTree[(i + 1) * 2];
            String concatHash = leftHash + rightHash;

            if (concatHash == "") {
                // If both left and right hashes are empty (Hash attempt of padded values),
                // return nothing, since the hash of the empty string "" is still valid and it
                // would modify the final resulting hash
                merkleTree[i] = "";
            } else {
                // Get the SHA-256 hash
                try {
                    merkleTree[i] = Encrypt.toHexString(Encrypt.getSHA(concatHash));
                } catch (NoSuchAlgorithmException e) {
                    System.err.println("ERROR: Unable to get SHA-256 hash");
                    System.exit(1);
                }
            }
        }

        return merkleTree;
    }

    // public static ArrayList<String> getMembershipProof(ArrayList<Account>
    // accList) {
    // ArrayList<String> proof;
    // return proof;
    // }
}
