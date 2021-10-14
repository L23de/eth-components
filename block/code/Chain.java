package block.code;

import merkle_root.code.*;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.ListIterator;

public class Chain {
    public static String chainFormat(LinkedList<Block> ledger, Boolean verbosity) {
        StringBuilder output = new StringBuilder();
        ListIterator<Block> iterator = ledger.listIterator();
        for (int i = 0; i < ledger.size(); i++) {
            Block block = iterator.next();
            output.append("BEGIN BLOCK\n");
            output.append("\tBEGIN HEADER\n");
            output.append(block.toString());
            output.append("\tEND HEADER\n");
            if (verbosity) {
                output.append("\tBEGIN BODY\n");
                ArrayList<Account> accList = block.accountList;
                for (int j = 0; j < accList.size(); j++) {
                    // if (accList.get(j).exist())
                    //     continue;
                    output.append("\t\t\t{}\n".format(accList.get(j).toString()));
                }
                output.append("\tEND BODY\n");
            }
            output.append("END BLOCK\n\n");
        }
        return output.toString();
    }
}
