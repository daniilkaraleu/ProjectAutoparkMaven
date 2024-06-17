package UtilFiles;
import Classes.Vehicle;

import java.util.Map;
import java.util.regex.Pattern;

public class LineProcessor {
    static final String REGEX_PATTERN = "\",|,\"|,(?![0-9]+\")";
    private LineProcessor(){}
    public static String[] splitLine(String line) {
        Pattern pattern = Pattern.compile(REGEX_PATTERN);

        return pattern.splitAsStream(line)
                .map(str -> str.replace(',', '.').replace('\"', ' ').strip())
                .toArray(String[]::new);
    }

    public static String transformToCSVLine(Vehicle vehicle, Map<String, Integer> detailsToRepair) {
        StringBuilder result = new StringBuilder(vehicle.getVehicleId() + ",");

        detailsToRepair.entrySet()
                .stream()
                .filter(entry -> entry.getValue() != 0)
                .forEach((entry) -> result.append(entry.getKey()).append(',').append(entry.getValue()).append(','));

        result.deleteCharAt(result.lastIndexOf(","));
        result.append("\n");
        return result.toString();
    }
}
