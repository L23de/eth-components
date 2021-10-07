package block.code;

import merkle_root.code.*;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.ArrayList;

public class Block {
    protected final double SUCCESS_RATE = 0.5;

    String prevHash;
    String merkleRoot;
    long timestamp;
    BigInteger diffTarget;
    String nonce;
    ArrayList<Account> accountList;

    public Block(String prevHash, String merkleRoot, String nonce, ArrayList<Account> acc) {
        this.prevHash = prevHash;
        this.merkleRoot = merkleRoot; // Set merkle root
        timestamp = System.currentTimeMillis() / 1000L; // unix time in seconds
        diffTarget = BigInteger.ONE.shiftLeft(256).subtract(BigInteger.ONE)
                .divide(new BigInteger(String.valueOf((int) (1 / SUCCESS_RATE))));
        this.nonce = nonce;
        accountList = acc;
    }

    public String toString() {
        return String.format(
                "\t\tPrevious Hash: %s\n\t\tMerkle Root: %s\n\t\tTimestamp: %s\n\t\tTarget: %s\n\t\tNonce: %s\n",
                prevHash, merkleRoot, String.valueOf(timestamp), this.diffTarget.toString(16), nonce);
    }

    public Boolean checkNonce() {
        String concat = "";
        try {
            concat = Encrypt.toHexString(Encrypt.getSHA(this.merkleRoot + this.nonce));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("ERROR: Unable to get SHA-256 hash");
            System.exit(1);
        }

        BigInteger checkHash = new BigInteger(concat, 16);
        // 1 - >
        // 0 - =
        // -1 - <
        if (checkHash.compareTo(this.diffTarget) <= 0) {
            return true;
        }
        return false;
    }

    // Calculate a random nonce of size 10
    // Source:
    // https://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
    public static String genNonce() {
        final String SALT_CHARS = "ABCDEF1234567890";
        StringBuilder newNonce = new StringBuilder();
        Random rnd = new Random();
        while (newNonce.length() < 10) {
            int index = (int) (rnd.nextFloat() * SALT_CHARS.length());
            newNonce.append(SALT_CHARS.charAt(index));
        }
        return newNonce.toString();
    }

    public ArrayList<Account> getAccountList() {
        return accountList;
    }
}
