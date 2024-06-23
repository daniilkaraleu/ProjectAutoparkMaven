package Project.Classes.Collections.ObjectCreator;

import Project.Classes.Collections.ObjectCreator.interfaces.Creator;
import Project.Classes.Infrastructure.dto.entity.Vehicles;
import Project.Classes.UtilFiles.LineProcessor;

public class CreateVehicles implements Creator<Vehicles> {

    public Vehicles createObject(String line) {
        String []data = LineProcessor.splitLine(line);
        Long vehicleType = Long.parseLong(data[1]);
        String model = data[2];
        String registrationNumber = data[3];
        Double mass = Double.parseDouble(data[4]);
        Integer yearOfManufacture = Integer.parseInt(data[5]);
        Double mileage = Double.parseDouble(data[6]);
        String engine = data[8];
        Double tankVolume = 10d;

        return Vehicles.builder()
                .type(vehicleType)
                .mass(mass)
                .mileage(mileage)
                .model(model)
                .registrationNumber(registrationNumber)
                .yearOfManufacture(yearOfManufacture)
                .tankVolume(tankVolume)
                .engine(engine)
                .color(data[7])
                .build();
    }

}
