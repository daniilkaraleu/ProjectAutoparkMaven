package UtilFiles;

import Classes.Engines.*;
import Classes.VehicleColors;
import Classes.VehicleType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TechnicalSpecialist {
    private static final int LOWER_LIMIT_MANUFACTURE_YEAR = 1886;
    private static final int UPPER_LIMIT_MANUFACTURE_YEAR = 2024;
    private static final String REGISTRATION_NUMBER_REGEX_PATTERN = "[0-9]{4} [A-Z]{2}-[0-9]";

    public TechnicalSpecialist() {
    }

    public static boolean validateManufactureYear(int year) {
        return year >= LOWER_LIMIT_MANUFACTURE_YEAR && year <= UPPER_LIMIT_MANUFACTURE_YEAR;
    }

    public boolean validateMileage(double mileage) {
        return mileage > 0;
    }

    public boolean validateWeight(double weight) {
        return weight > 0;
    }

    public boolean validateColor(VehicleColors color) {
        return color != null;
    }

    public boolean validateVehicleType(VehicleType type) {
        return !(type.getTaxCoefficient() <= 0) && type.getType() != null;
    }

    public boolean validateRegistrationNumber(String registrationNumber) {
        Pattern pattern = Pattern.compile(REGISTRATION_NUMBER_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(registrationNumber);
        return matcher.matches();

    }

    public boolean validateModelName(String name) {
        return !name.isBlank();
    }

    public boolean validateEngine(Engine engine) {
        if (engine instanceof ElectricalEngine) {
            return validateElectricalEngine((ElectricalEngine) engine);
        } else {
            return validateCombustionEngine((CombustionEngine) engine);
        }
    }

    private boolean validateCombustionEngine(CombustionEngine engine) {
        return engine.getEngineCapacity() >= 0 && engine.getFuelConsumptionPer100() >= 0 && engine.getFuelTankCapacity() >= 0 &&
                !engine.getName().isBlank() && engine.getTaxPerMonth() >= 0;
    }

    private boolean validateElectricalEngine(ElectricalEngine engine) {
        return engine.getBatteryCharge() >= 0 && engine.getElectricityConsumption() >= 0 &&
                !engine.getName().isBlank() && engine.getTaxPerMonth() >= 0;
    }

}
