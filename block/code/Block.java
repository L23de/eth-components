package block.code;

import merkle_root.code.*;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Block {
    protected static final double SUCCESS_RATE = 0.5;

    String prevHash;
    String merkleRoot;
    long timestamp;
    double diffTarget;
    String nonce;

    public Block(String prevHash, String merkleRoot, String nonce) {
        this.prevHash = prevHash;
        this.merkleRoot = merkleRoot; // Set merkle root
        timestamp = System.currentTimeMillis() / 1000L; // unix time in seconds
        this.diffTarget = 0.5;
        this.nonce = nonce;
    }

    public static Boolean checkNonce(String merkleRoot, String nonce) {
        String concat = "";
        try {
            concat = Encrypt.toHexString(Encrypt.getSHA(merkleRoot + nonce));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Unable to get SHA-256 hash for leaf nodes");
            System.exit(1);
        }

        BigInteger target = BigInteger.ONE.shiftLeft(256).subtract(BigInteger.ONE)
                .divide(new BigInteger(String.valueOf((int) (1 / SUCCESS_RATE))));

        BigInteger checkHash = new BigInteger(concat);
        // 1 - >
        // 0 - =
        // -1 - <
        if (checkHash.compareTo(target) <= 0) {
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
}
