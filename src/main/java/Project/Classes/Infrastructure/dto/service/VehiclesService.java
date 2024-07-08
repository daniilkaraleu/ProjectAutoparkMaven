package Project.Classes.Infrastructure.dto.service;

import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.core.annotations.InitMethod;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.Service;
import Project.Classes.Infrastructure.dto.entity.VehicleDTO;

import java.util.List;

public class VehiclesService implements Service<VehicleDTO> {
    private static final String CSV = "vehicles";
    @Autowired
    EntityManager entityManager;

    public VehiclesService(){}
    @InitMethod
    public void init(){
    }
    public VehicleDTO get(Long id){
        return entityManager.get(id, VehicleDTO.class).orElseGet(VehicleDTO::new);
    }

    public List<VehicleDTO> getAll(){
        return entityManager.getAll(VehicleDTO.class);
    }

    public Long save(VehicleDTO vehicle){
        return entityManager.save(vehicle);
    }
}
