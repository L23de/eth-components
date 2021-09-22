package merkle_root.code;

import java.security.NoSuchAlgorithmException;

public class Account {
    String address;
    String balance;
    String hash;

    public Account(String entry) {
        if (entry == null) { // for padded entries
            address = null;
            balance = null;
            hash = null;
        } else { // for "normal" string entries
            String[] accountInfo = entry.split(" ", 2);
            address = accountInfo[0];
            balance = accountInfo[1];
            try {
                hash = Encrypt.toHexString(Encrypt.getSHA(address + balance));
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Unable to get SHA-256 hash for leaf nodes");
                System.exit(1);
            }
        }
    }

    public String toString() {
        return String.format("Address: %s\nBalance: %s\nHash: %s\n\n", address, balance, hash);
    }
}
