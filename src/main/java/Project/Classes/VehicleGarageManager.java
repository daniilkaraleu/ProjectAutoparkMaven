package Project.Classes;

import Project.Classes.Infrastructure.dto.entity.MapperForDB;
import Project.Classes.Infrastructure.dto.service.RentsService;
import Project.Classes.Infrastructure.dto.service.VehiclesService;
import Project.Classes.Infrastructure.threads.annotations.Schedule;

public class VehicleGarageManager {
    private final int DELTA = 10;
    private final int TIMEOUT = 10;

    @Schedule(delta = DELTA, timeout = TIMEOUT)
    public void vehicleToGarage(VehiclesService vehiclesService, MechanicService mechanicService, RentsService rentsService){

        vehiclesService
                .getAll()
                .stream()
                .map(vehicles -> MapperForDB.createVehicle(vehicles, rentsService.getAll()))
                .forEach(mechanicService::detectBreaking);
    }
}
