package code;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        // Makes sure that only 2 arguments are provided
        int argLength = args.length;
        if (argLength > 1) {
            System.err.println(
                    "Too many arguments were provided. Please only provide the filename with a list of addresses and balances");
            System.exit(1);
        } else if (argLength < 1) {
            System.err.println(
                    "Not enough arguments were provided. Please provide the filename with a list of addresses and balances");
            System.exit(1);
        }

        // Each element in the ledger is a concatenated string of the balance and
        // transaction
        ArrayList<Account> ledger = Input.parseFile(args[0]);
        // Print out the ledger (ArrayList) using prebuild toString() method
        // System.out.println(ledger.toString());
        System.out.println("Merkle Root: " + Hash.getMerkleRoot(ledger, 0, ledger.size() - 1));
    }
}
