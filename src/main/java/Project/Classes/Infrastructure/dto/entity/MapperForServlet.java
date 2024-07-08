package Project.Classes.Infrastructure.dto.entity;

import Project.Classes.CollectionManager;
import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Vehicle;

import java.util.List;

public class MapperForServlet {

    @Autowired
    private static CollectionManager collectionManager;
    public MapperForServlet(){}

    public static List<VehicleDTO> getVehiclesDTOForReport(){

        List<Vehicle> list = collectionManager.getVehicleCollection().getList();

        return list.stream().map(vehicle -> VehicleDTO.builder()
                .model(vehicle.getModel())
                .type(vehicle.getVehicleType().getTypeId())
                .color(vehicle.getColor().name())
                .yearOfManufacture(vehicle.getYearOfManufacture())
                .engine(vehicle.getEngine().getName())
                .mass(vehicle.getMass())
                .mileage(vehicle.getMileage())
                .registrationNumber(vehicle.getRegistrationNumber())
                .rentIncome(vehicle.getRentsIncome())
                .tax(vehicle.getCalcTaxPerMonth())
                .income(vehicle.getTotalProfit())
                .build()).toList();
    }
    public static List<VehicleDTO> getVehiclesDTOForDiagnostics(){

        List<Vehicle> list = collectionManager.getVehicleCollection().getList();

        return list.stream().map(vehicle -> VehicleDTO.builder()
                .model(vehicle.getModel())
                .type(vehicle.getVehicleType().getTypeId())
                .color(vehicle.getColor().name())
                .yearOfManufacture(vehicle.getYearOfManufacture())
                .engine(vehicle.getEngine().getName())
                .mass(vehicle.getMass())
                .mileage(vehicle.getMileage())
                .registrationNumber(vehicle.getRegistrationNumber())
                .tankVolume(vehicle.getTankVolume())
                .wasBroken(vehicle.getWasWorking())
                .fixed(vehicle.getFixed())
                .build()).toList();
    }
}