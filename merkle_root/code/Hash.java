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

    public static ArrayList<String> getMembershipProof(ArrayList<Account> accList, String address, int idx) {
        ArrayList<String> proof = new ArrayList<>();

        // Gets merkle tree
        String[] merkleTree = getMerkleTree(accList, accList.size()-1);

        int index = idx;
        while (index > 0) {
            proof.add(merkleTree[index]);
            proof.add(merkleTree[getSiblingIdx(index)]);
            index = getParentIdx(index); // Should always terminate at 0
        }
        // Add the merkleRoot at the end
        proof.add(merkleTree[0]);
        
        return proof;
    }
    // public static ArrayList<String> getMembershipProof(ArrayList<Account> accList) {
    //     ArrayList<String> proof;
    //     return proof;
    // }

    public static int getParentIdx(int currIdx) {
        return (currIdx - 1) / 2;
    }

    public static int getSiblingIdx(int currIdx) {
        if (currIdx % 2 == 0) { // If even
            return currIdx - 1;
        }
        return currIdx + 1;
    }

}
