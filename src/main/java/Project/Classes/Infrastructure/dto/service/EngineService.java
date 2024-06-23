package Project.Classes.Infrastructure.dto.service;

import Project.Classes.Collections.ObjectCreator.CreateEngines;
import Project.Classes.Collections.ObjectCreator.ParseVehicleFromFileToDB;
import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.core.annotations.InitMethod;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.Service;
import Project.Classes.Infrastructure.dto.entity.Engines;

import java.util.List;

public class EngineService implements Service<Engines> {
    private static final String CSV = "vehicles";
    @Autowired
    EntityManager entityManager;
    @Autowired
    ParseVehicleFromFileToDB parseVehicleFromFileToDB;
    @InitMethod
    @Override
    public void init() {
        parseVehicleFromFileToDB.loadCollection(CSV, new CreateEngines(), this);
    }

    @Override
    public Engines get(Long id) {
        return entityManager.get(id, Engines.class).orElseGet(Engines::new);
    }

    @Override
    public List<Engines> getAll() {
        return entityManager.getAll(Engines.class);
    }

    @Override
    public Long save(Engines type) {
        return entityManager.save(type);
    }
}
