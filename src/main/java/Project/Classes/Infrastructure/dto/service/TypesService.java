package Project.Classes.Infrastructure.dto.service;

import Project.Classes.Collections.ObjectCreator.CreateTypes;
import Project.Classes.Collections.ObjectCreator.ParseVehicleFromFileToDB;
import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.core.annotations.InitMethod;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.Service;
import Project.Classes.Infrastructure.dto.entity.Types;

import java.util.List;

public class TypesService implements Service<Types> {
    private static final String CSV = "types";

    public TypesService(){}

    @Autowired
    EntityManager entityManager;
    @Autowired
    ParseVehicleFromFileToDB parseVehicleFromFileToDB;

    @InitMethod
    public void init(){
        parseVehicleFromFileToDB.loadCollection(CSV, new CreateTypes(), this);
    }
    public Types get(Long id){
        return entityManager.get(id, Types.class).orElseGet(Types::new);
    }

    public List<Types> getAll(){
        return entityManager.getAll(Types.class);
    }

    public Long save(Types type){
        return entityManager.save(type);
    }
}
