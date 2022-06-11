package team5.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class RecordWriter {

    public static void writeFile(String stringToPrint) {
        File file = new File("./src/main/resources/vaccination-results.txt");
        try {
            FileUtils.writeStringToFile(file, stringToPrint, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

