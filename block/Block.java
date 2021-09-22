package block;

import java.sql.Timestamp;

public class Block {
    String prevHash;
    String merkleRoot;
    Timestamp timestamp;
    float diffTarget;
    String nonce;

    public Block() {}
}
