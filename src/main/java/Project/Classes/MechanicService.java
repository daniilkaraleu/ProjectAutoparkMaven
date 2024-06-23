package Project.Classes;

import Project.Classes.interfaces.Fixer;
import Project.Classes.UtilFiles.CSVReadWrite;
import Project.Classes.UtilFiles.LineProcessor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class MechanicService implements Fixer {
    public static final String[] DETAILS = {"Фильтр", "Втулка", "Вал", "Ось", "Свеча", "Масло", "ГРМ", "Шрус"};
    final Path FILE_PATH = (Paths.get("D:/JavaProjects/AutoparkProject/src/CSV/orders.csv"));
    static final int AMOUNT_OF_BROKEN_DETAILS = 3;
    final Charset utf8 = StandardCharsets.UTF_8;

    public MechanicService(){}
    public List<Vehicle> findNotBrokenVehicles(List<Vehicle> vehicles) {
        return vehicles.stream().filter(vehicle -> !isBroken(vehicle)).collect(Collectors.toList());
    }

    @Override
    public Map<String, Integer> detectBreaking(Vehicle vehicle) {
        Map<String, Integer> detailsToRepair = new HashMap<>();

        Arrays.stream(DETAILS)
                .forEach(x -> detailsToRepair.put(x, ((int) (Math.random() * AMOUNT_OF_BROKEN_DETAILS))));

        CSVReadWrite.makeRecord(LineProcessor.transformToCSVLine(vehicle, detailsToRepair), FILE_PATH);

        return detailsToRepair.entrySet()
                .stream()
                .filter(x -> x.getValue() != 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void repair(Vehicle vehicle) {
        if (isBroken(vehicle)) {
            System.out.println(vehicle.getModel() + " Was fixed");
            CSVReadWrite.clearFile(FILE_PATH);
        }
    }

    @Override
    public boolean isBroken(Vehicle vehicle) {
        List<String> vehiclesID = CSVReadWrite.readCSV(FILE_PATH);
        for (String vehicleID : vehiclesID) {
            if (vehicleID.charAt(0) - '0' == vehicle.getVehicleId()) {
                return true;
            }
        }
        return false;
    }
}
