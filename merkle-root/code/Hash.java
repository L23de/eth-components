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
        // "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855" is the
        // hash of an empty string
        String leftHash = getMerkleRoot(ledger, start, mid);
        String rightHash = getMerkleRoot(ledger, mid + 1, end);
        String concatHash = leftHash + rightHash;

        if (concatHash == "") {
            return "";
        }

        try {
            return Encrypt.toHexString(Encrypt.getSHA(concatHash));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("ERROR: Unable to get SHA-256 hash");
            System.exit(1);
        }
        return "";
    }
}
