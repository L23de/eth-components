package block.code;

import merkle_root.code.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Output {
    public static void writeFile(String fileName, String toWrite) {
        System.out.println(fileName);
        String newFileName = fileName.replace(".txt", "");
        newFileName = newFileName.replace("files/", "") + ".block.out";
        System.out.println(newFileName);
        File file = new File(newFileName);
        FileWriter scribe = null;
        try {
            file.createNewFile();
            scribe = new FileWriter(file);
            scribe.write(toWrite);
            scribe.close();
        } catch (IOException e) {
            System.err.println("ERROR: Unable to create new file");
            e.printStackTrace();
        }

    }
}