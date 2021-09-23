package block.code;

import merkle_root.code.*;
import java.sql.Timestamp;
import java.util.Date;

public class Block {
    String prevHash;
    String merkleRoot;
    Timestamp timestamp;
    double diffTarget;
    String nonce;

    public Block(String prevHash, String merkleRoot) {
        this.prevHash = prevHash;
        this.merkleRoot = merkleRoot; // Set merkle root
        timestamp = new Timestamp(new Date().getTime()); // Get current timestamp on creation
        this.diffTarget = 0.5; // Hard code to 50% success
    }
}
