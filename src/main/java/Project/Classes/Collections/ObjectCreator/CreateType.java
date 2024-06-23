package Project.Classes.Collections.ObjectCreator;

import Project.Classes.Engines.DieselEngine;
import Project.Classes.Engines.ElectricalEngine;
import Project.Classes.Engines.Engine;
import Project.Classes.Engines.GasolineEngine;
import Project.Classes.Rent;
import Project.Classes.Vehicle;
import Project.Classes.VehicleColors;
import Project.Classes.VehicleType;
import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.dto.entity.Engines;
import Project.Classes.Infrastructure.dto.entity.Rents;
import Project.Classes.Infrastructure.dto.entity.Types;
import Project.Classes.Infrastructure.dto.entity.Vehicles;
import Project.Classes.Infrastructure.dto.impl.ParserVehiclesFromDB;

public class CreateType  {

    @Autowired
    private static ParserVehiclesFromDB parserFromDB;

    public CreateType(){}
    public static VehicleType createVehicleType(Types types){
        return new VehicleType(types.getId(), types.getName(), types.getCoefTaxes());
    }
    public static Vehicle createVehicle(Vehicles vehicles){
        VehicleColors color = VehicleColors.valueOf(vehicles.getColor());
        Engine engine = createEngine(parserFromDB.getEngineService().get(vehicles.getId()));
        VehicleType type = createVehicleType(parserFromDB.getTypesService().get(vehicles.getType()));

        return new Vehicle(vehicles.getId(), type, vehicles.getModel(), vehicles.getRegistrationNumber(), vehicles.getMass(),
                vehicles.getYearOfManufacture(), vehicles.getMileage(), color, engine, vehicles.getTankVolume(), null);
    }
    public static Rent createRent(Rents rents){
        return Rent.builder()
                .rentCost(rents.getRentCost())
                .rentDate(rents.getRentDate())
                .vehicleId(rents.getVehicleId())
                .build();
    }

    public static Engine createEngine(Engines engines){
        if (engines.getName().equalsIgnoreCase("Diesel")) {
            return new DieselEngine(engines.getEngineCapacity(), engines.getFuelConsumption(), engines.getEngineCapacity());
        }
        else if (engines.getName().equalsIgnoreCase("Gasoline")) {
            return new GasolineEngine(engines.getEngineCapacity(), engines.getFuelConsumption(), engines.getEngineCapacity());
        } else {
            return new ElectricalEngine(engines.getFuelConsumption(), engines.getTankCapacity());
        }
    }

}
