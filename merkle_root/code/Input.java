package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {

    /**
     * Parses a ledger file and creates an ArrayList of Accounts
     * 
     * @param: String of file name
     * @exception: File not found
     * @return: Account ArrayList of file contents
     */

    public static ArrayList<Account> parseFile(String filename) {
        // The file object to read from
        File myFile = new File(filename);
        Scanner fileScanner = null;
        try {
            // if the file exists
            fileScanner = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File not found.");
            e.printStackTrace();
        }

        // the ArrayList to be returned
        ArrayList<Account> myList = new ArrayList<Account>();

        while (fileScanner.hasNextLine()) {
            String data = fileScanner.nextLine();
            Account newAcc = new Account(data);
            myList.add(newAcc);
        }

        // close the file scanner
        fileScanner.close();

        // padding
        int levels = log2(myList.size());
        int maxSize = (int) Math.pow(2, levels);
        while (myList.size() != maxSize) {
            Account padAcc = new Account(null);
            myList.add(padAcc);
        }
        return myList;
    }

    // Function to calculate the
    // log base 2 of an integer
    // Source:
    // https://www.geeksforgeeks.org/how-to-calculate-log-base-2-of-an-integer-in-java/
    public static int log2(int N) {
        // calculate log2 N indirectly
        // using log() method
        int result = (int) Math.ceil(Math.log(N) / Math.log(2));
        return result;
    }
}
