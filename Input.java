import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads a file and
 */
public class Input {

    /**
     * @param: string of file name
     * @exception: File not found
     * @return: String ArrayList of file contents
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
            // System.out.println(data);
            Account newAcc = new Account(data);
            System.out.println(newAcc.toString());
            System.out.println();
            // add the string data to ArrayList
            myList.add(newAcc);
        }

        // close the file scanner
        fileScanner.close();

        return myList;
    }
}
