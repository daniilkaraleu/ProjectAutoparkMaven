package Project.Classes.Infrastructure.dto.service;

import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.core.annotations.InitMethod;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.Service;
import Project.Classes.Infrastructure.dto.entity.Vehicles;
import Project.Classes.Collections.ObjectCreator.CreateVehicles;
import Project.Classes.Collections.ObjectCreator.ParseVehicleFromFileToDB;

import java.util.List;

public class VehiclesService implements Service<Vehicles> {
    private static final String CSV = "vehicles";
    @Autowired
    EntityManager entityManager;
    @Autowired
    ParseVehicleFromFileToDB parseVehicleFromFileToDB;

    public VehiclesService(){}
    @InitMethod
    public void init(){
        parseVehicleFromFileToDB.loadCollection(CSV, new CreateVehicles(), this);
    }
    public Vehicles get(Long id){
        return entityManager.get(id, Vehicles.class).orElseGet(Vehicles::new);
    }

    public List<Vehicles> getAll(){
        return entityManager.getAll(Vehicles.class);
    }

    public Long save(Vehicles vehicle){
        return entityManager.save(vehicle);
    }
}
