package Project.Classes.Infrastructure.dto.entity.mappers;

import Project.Classes.CollectionManager;
import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.dto.entity.OrderDTO;
import Project.Classes.Infrastructure.dto.entity.VehicleDTO;
import Project.Classes.Order;
import Project.Classes.Vehicle;

import java.util.List;
import java.util.Optional;

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

        List<Vehicle> vehicleList = collectionManager.getVehicleCollection().getList();
        List<Order> orderList = collectionManager.getOrderCollection().getList();

        return  vehicleList
                .stream()
                .map(vehicle ->
                        VehicleDTO.builder()
                                .model(vehicle.getModel())
                                .type(vehicle.getVehicleType().getTypeId())
                                .color(vehicle.getColor().name())
                                .yearOfManufacture(vehicle.getYearOfManufacture())
                                .engine(vehicle.getEngine().getName())
                                .mass(vehicle.getMass())
                                .mileage(vehicle.getMileage())
                                .registrationNumber(vehicle.getRegistrationNumber())
                                .tankVolume(vehicle.getTankVolume())
                                .wasBroken(orderList
                                        .stream()
                                        .map(Order::getVehicle)
                                        .anyMatch(vehicleFromOrder -> vehicleFromOrder.equals(vehicle)))
                                .fixed(vehicle.getFixed())
                                .build())
                .toList();
    }
    public static List<VehicleDTO> getVehiclesDTOForCars(){

        List<Vehicle> list = collectionManager.getVehicleCollection().getList();

        return list.stream().map(vehicle -> VehicleDTO.builder()
                .id(vehicle.getVehicleId())
                .model(vehicle.getModel())
                .type(vehicle.getVehicleType().getTypeId())
                .color(vehicle.getColor().name())
                .yearOfManufacture(vehicle.getYearOfManufacture())
                .engine(vehicle.getEngine().getName())
                .mass(vehicle.getMass())
                .mileage(vehicle.getMileage())
                .registrationNumber(vehicle.getRegistrationNumber())
                .tankVolume(vehicle.getTankVolume())
                .build()).toList();
    }
}
