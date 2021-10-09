package validation.code;

import merkle_root.code.*;
import block.code.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.ListIterator;

public class App {
    public static void main(String[] args) throws InterruptedException {

        int argLength = args.length;
        if (argLength < 1) {
            System.err.println(
                    "Not enough arguments were provided. Please provide the filenames containing addresses and balances");
            System.exit(1);
        }

        LinkedList<Block> blockchain = new LinkedList<Block>();

        for (int i = 0; i < args.length; i++) {

            Input.parseFile(args[i]);
        }

    }
}
