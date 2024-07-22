package Project.Classes.Infrastructure.dto.entity.mappers;

import Project.Classes.*;
import Project.Classes.Engines.DieselEngine;
import Project.Classes.Engines.ElectricalEngine;
import Project.Classes.Engines.Engine;
import Project.Classes.Engines.GasolineEngine;
import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.dto.entity.EngineDTO;
import Project.Classes.Infrastructure.dto.entity.RentDTO;
import Project.Classes.Infrastructure.dto.entity.TypeDTO;
import Project.Classes.Infrastructure.dto.entity.VehicleDTO;
import Project.Classes.Infrastructure.dto.impl.ParserVehiclesFromDB;
import Project.Classes.UtilFiles.LineProcessor;

import java.util.List;

public class MapperForDB {

    @Autowired
    private static ParserVehiclesFromDB parserFromDB;


    public MapperForDB(){}

    public static VehicleType createVehicleType(TypeDTO types){
        return new VehicleType(types.getId(), types.getName(), types.getCoefTaxes());
    }
    public static Vehicle createVehicle(VehicleDTO vehicles, List<RentDTO> rentsList){

        VehicleColors color = VehicleColors.valueOf(vehicles.getColor());
        Engine engine = createEngine(parserFromDB.getEngineService().get(vehicles.getId()));
        VehicleType type = createVehicleType(parserFromDB.getTypesService().get(vehicles.getType()));
        List<Rent> rentList = rentsList.stream().filter(rents -> rents.getVehicleId().equals(vehicles.getId())).map(MapperForDB::createRent).toList();

        return new Vehicle(vehicles.getId(), type, vehicles.getModel(), vehicles.getRegistrationNumber(), vehicles.getMass(),
                vehicles.getYearOfManufacture(), vehicles.getMileage(), color, engine, vehicles.getTankVolume(), rentList);
    }
    public static Rent createRent(RentDTO rents){
        return Rent.builder()
                .rentCost(rents.getRentCost())
                .rentDate(rents.getRentDate())
                .vehicleId(rents.getVehicleId())
                .build();
    }

    public static Engine createEngine(EngineDTO engineDTO){
        if (engineDTO.getName().equalsIgnoreCase("Diesel")) {
            return new DieselEngine(engineDTO.getEngineCapacity(), engineDTO.getFuelConsumption(), engineDTO.getEngineCapacity());
        }
        else if (engineDTO.getName().equalsIgnoreCase("Gasoline")) {
            return new GasolineEngine(engineDTO.getEngineCapacity(), engineDTO.getFuelConsumption(), engineDTO.getEngineCapacity());
        } else {
            return new ElectricalEngine(engineDTO.getFuelConsumption(), engineDTO.getTankCapacity());
        }
    }

    public static EngineDTO createEngineDTO(String line) {
        String []data = LineProcessor.splitLine(line);

        Double engineCapacity = data[8].equals("Electrical") ? null : Double.parseDouble(data[9]);
        Double fuelConsumption = data[8].equals("Electrical") ? Double.parseDouble(data[9]) : Double.parseDouble(data[10]);
        Double tankCapacity = data[8].equals("Electrical") ? Double.parseDouble(data[10]) : Double.parseDouble(data[11]);


        return EngineDTO.builder()
                .name(data[8])
                .engineCapacity(engineCapacity)
                .fuelConsumption(fuelConsumption)
                .tankCapacity(tankCapacity)
                .build();
    }

    public static RentDTO createRentDTO(String line){
        String []data = LineProcessor.splitLine(line);

        return RentDTO.builder()
                .vehicleId(Long.parseLong(data[0]))
                .rentDate(data[1])
                .rentCost(Double.parseDouble(data[2]))
                .build();
    }

    public static TypeDTO createVehicleTypeDTO(String line){
        String []data = LineProcessor.splitLine(line);

        return TypeDTO.builder()
                .name(data[1])
                .coefTaxes(Double.parseDouble(data[2]))
                .build();
    }

    public static VehicleDTO createVehicleDTO(String line) {
        String []data = LineProcessor.splitLine(line);

        Long vehicleType = Long.parseLong(data[1]);
        String model = data[2];
        String registrationNumber = data[3];
        Double mass = Double.parseDouble(data[4]);
        Integer yearOfManufacture = Integer.parseInt(data[5]);
        Double mileage = Double.parseDouble(data[6]);
        String engine = data[8];
        Double tankVolume = 10d;

        return VehicleDTO.builder()
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
