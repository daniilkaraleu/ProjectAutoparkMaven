package Classes.Collections.interfaces;

import Classes.Collections.ObjectCreator.interfaces.Creator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public interface AutoparkCollection<T> {
    default public List<T> loadCollection(String fromCSV, Creator<T> creator) {
        String filePath = "D:/JavaProjects/AutoparkProject/src/CSV/" + fromCSV + ".csv";
        List <T> list = new ArrayList<>();

        try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath))) {
            String line = csvReader.readLine();

            while (line != null) {
                list.add(creator.createObject(line));
                line = csvReader.readLine();
            }
        } catch (Exception e) {

            System.out.println(e.getMessage() + " " + e.getCause());
        }
        return list;
    }
}
