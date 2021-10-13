package validation.code;

import merkle_root.code.*;
import block.code.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList;

public class Input {

    /**
     * Parses a block.out file and creates a LinkedList of Blocks
     * 
     * @param: String of file name
     * @exception: File not found
     * @return: myChain LinkedList of block contents
     */

    public static LinkedList<Block> parseFile(String filename) {
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
        LinkedList<Block> myChain = new LinkedList<Block>();

        while (fileScanner.hasNextLine()) {
            String data = fileScanner.nextLine();
            //System.out.println("Block begin scanned is " + data);

            if (data.compareTo("BEGIN BLOCK") == 0) {
              data = fileScanner.nextLine();
              if (data.compareTo("\tBEGIN HEADER") == 0) {
                  //System.out.println("Header begin is " + data);
                  data = fileScanner.nextLine();
                  //System.out.println("Next line is " + data);
                  if (data.startsWith("\t\tPrevious Hash: ")) {
                    String prevHash = data.split(": ")[1];
                    //System.out.println("\n\n\n\n -----Prev Hash " + prevHash);
                    data = fileScanner.nextLine();

                    if (data.startsWith("\t\tMerkle Root: ")) {
                    String merkleRoot = data.split(": ")[1];
                    //System.out.println("\n\n\n\n -----merkle root " + merkleRoot);
                    data = fileScanner.nextLine(); // Timestamp
                    //System.out.println("Data 1 is " + data);
                    data = fileScanner.nextLine(); // Target
                    //System.out.println("Data 2 is " + data);
                    data = fileScanner.nextLine();
                      if (data.startsWith("\t\tNonce: ")) {
                        String nonce = data.split(": ")[1];
                        //System.out.println("\n\n\n\n -----nonce " + nonce);
                        data = fileScanner.nextLine(); // END HEADER
                        data = fileScanner.nextLine(); // BEGIN BODY

                        ArrayList<Account> acc = new ArrayList<>();
                        while (data.compareTo("\tEND BODY") != 0) {
                            data = fileScanner.nextLine();
                            if (data.startsWith("\t\tAddress: ")) {
                              String address = data.split(": ")[1];
                              //System.out.println("\n\n\n\n -----address " + address);
                                data = fileScanner.nextLine();
                      
                                if (data.startsWith("\t\tBalance: ")) {
                                  String balance = data.split(": ")[1];
                                  //System.out.println("\n\n\n\n -----balance " + balance);

                                  String entry = address + " " + balance;
                                  //System.out.println("entry " + entry);
                                  Account accountEntry = new Account(entry);
                                  acc.add(accountEntry);
                                }
                            }
                        }
                        Block newBlock = new Block(prevHash, merkleRoot, nonce, acc);
                        myChain.push(newBlock);
                        data = fileScanner.nextLine(); // END BLOCK
                        data = fileScanner.nextLine(); // new line
                      }
                    }
                }

              }
            }
        }

        // close the file scanner
        fileScanner.close();
        return myChain;
    }
}
