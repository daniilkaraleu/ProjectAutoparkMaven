package Classes.Collections.ObjectCreator;

import Classes.Collections.RentCollection;
import Classes.Collections.TypesCollection;
import Classes.Engines.Engine;
import Classes.Rent;
import Classes.Vehicle;
import Classes.VehicleColors;
import Classes.VehicleType;
import Classes.Collections.ObjectCreator.interfaces.Creator;
import Infrastructure.core.annotations.Autowired;
import UtilFiles.LineProcessor;
import UtilFiles.TechnicalSpecialist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class CreateVehicle implements Creator<Vehicle> {
    List<VehicleType> types;
    List<Rent> rents;
    private final TechnicalSpecialist techSpecialist;


    public CreateVehicle(TypesCollection types, RentCollection rents) {
        this.types = types.getList();
        this.rents = rents.getList();
        this.techSpecialist = new TechnicalSpecialist();
    }


    public Vehicle createObject(String line) {
        String []data = LineProcessor.splitLine(line);

        CreateEngine engineCreator = new CreateEngine();

        int vehicleId = Integer.parseInt(data[0]);
        VehicleType vehicleType = types.get(Integer.parseInt(data[1]) - 1);
        String model = data[2];
        String registrationNumber = data[3];
        double mass = Integer.parseInt(data[4]);
        int yearOfManufacture = Integer.parseInt(data[5]);
        double mileage = Integer.parseInt(data[6]);
        VehicleColors color = VehicleColors.valueOf(data[7]);
        Engine engine = engineCreator.createEngine(data[8], data);
        double tankVolume = 10;
        List<Rent> vehicleRents= composeRents(vehicleId);

        return new Vehicle(vehicleId, vehicleType, model, registrationNumber, mass, yearOfManufacture, mileage, color, engine, tankVolume, vehicleRents);
    }

    private List<Rent> composeRents(int vehicleId) {
        List<Rent> vehicleRents = new ArrayList<>();

        for (Rent rent : rents) {
            if (vehicleId == rent.getVehicleId()) {
                vehicleRents.add(rent);
            }
        }
        return vehicleRents;
    }
}
