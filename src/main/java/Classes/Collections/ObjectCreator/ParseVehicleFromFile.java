package Classes.Collections.ObjectCreator;

import Classes.Collections.ObjectCreator.interfaces.Creator;
import Infrastructure.core.annotations.Autowired;
import UtilFiles.TechnicalSpecialist;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ParseVehicleFromFile {

    public ParseVehicleFromFile(){}

    public <T> List<T> loadCollection(String fromCSV, Creator<T> creator) {
        String filePath = "D:/ProjectMavenAutopark/src/main/resources/CSV/" + fromCSV + ".csv";
        List <T> list = new ArrayList<>();

        try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = csvReader.readLine()) != null) {
                list.add(creator.createObject(line));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " " + e.getCause());
        }
        return list;
    }
}
