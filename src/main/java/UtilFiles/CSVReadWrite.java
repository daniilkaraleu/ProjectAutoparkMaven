package UtilFiles;

import Classes.Vehicle;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSVReadWrite {
    static final Charset utf8 = StandardCharsets.UTF_8;
    private CSVReadWrite(){}

    public static void makeRecord(String str, Path FILE_PATH) {
        try {
            Files.writeString(FILE_PATH, str, StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void clearFile( Path FILE_PATH) {
        try {
            Files.writeString(FILE_PATH, "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    public static List<String> readCSV(Path FILE_PATH) {
        List<String> brokenDetails = new ArrayList<>();
        try {
            brokenDetails = Files.readAllLines(FILE_PATH, utf8);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return brokenDetails;
    }
}
