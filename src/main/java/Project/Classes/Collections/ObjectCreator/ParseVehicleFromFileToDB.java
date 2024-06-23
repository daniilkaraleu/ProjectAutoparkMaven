package Project.Classes.Collections.ObjectCreator;

import Project.Classes.Collections.ObjectCreator.interfaces.Creator;
import Project.Classes.Infrastructure.dto.Service;

import java.io.BufferedReader;
import java.io.FileReader;

public class ParseVehicleFromFileToDB {
    public ParseVehicleFromFileToDB(){}

    public <T> void loadCollection(String fromCSV, Creator<T> creator, Service<T> service) {
        String filePath = "D:/ProjectMavenAutopark/src/main/resources/CSV/" + fromCSV + ".csv";

        try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = csvReader.readLine()) != null) {
                service.save(creator.createObject(line));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " " + e.getCause());
        }
    }
}
