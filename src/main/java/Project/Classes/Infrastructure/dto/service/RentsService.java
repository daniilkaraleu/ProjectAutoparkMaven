package Project.Classes.Infrastructure.dto.service;

import Project.Classes.Collections.ObjectCreator.ParseVehicleFromFileToDB;
import Project.Classes.Collections.ObjectCreator.CreateRents;
import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.core.annotations.InitMethod;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.Service;
import Project.Classes.Infrastructure.dto.entity.Rents;

import java.util.List;

public class RentsService implements Service<Rents> {
    private static final String CSV = "rents";
    @Autowired
    EntityManager entityManager;
    @Autowired
    ParseVehicleFromFileToDB parseVehicleFromFileToDB;

    public RentsService(){}

    @InitMethod
    public void init(){
        parseVehicleFromFileToDB.loadCollection(CSV, new CreateRents(), this);
    }
    public Rents get(Long id){
        return entityManager.get(id, Rents.class).orElseGet(Rents::new);
    }

    public List<Rents> getAll(){
        return entityManager.getAll(Rents.class);
    }

    public Long save(Rents rent){
        return entityManager.save(rent);
    }
}
