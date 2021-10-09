package validation.code;

import merkle_root.code.*;
import block.code.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class Validate {
    
    // Check if the input block's merkle root matches with any of those in the chain
    public boolean checkBlock(LinkedList<Block> chain, Block inputBlock) {

        ListIterator<Block> iterator = chain.listIterator();
        for (int i = 0; i < chain.size(); i++) {
            Block block = iterator.next();

            if (block.getMerkleRoot().compareTo(inputBlock.getMerkleRoot()) == 0) {
                return true;
            }
        }
        return false;
    }


    public boolean checkChain(LinkedList<Block> chain, LinkedList<Block> inputChain) {
        ListIterator<Block> iterator1 = chain.listIterator();
        ListIterator<Block> iterator2 = inputChain.listIterator();
        if (chain.size() != inputChain.size()) {
            return false;
        }

        for (int i = 0; i < chain.size(); i++) {
            Block block1 = iterator1.next();
            Block block2 = iterator2.next();
            if (block1.getMerkleRoot().compareTo(block2.getMerkleRoot()) == 0) {
                if (block1.getPrevHash().compareTo(block2.getPrevHash()) == 0) {
                    continue;
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }

        return true;
    }
}
