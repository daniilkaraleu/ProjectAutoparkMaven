package Project.Classes.Infrastructure.dto.service;

import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.core.annotations.InitMethod;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.Service;
import Project.Classes.Infrastructure.dto.entity.EngineDTO;

import java.util.List;

public class EngineService implements Service<EngineDTO> {
    private static final String CSV = "vehicles";
    @Autowired
    EntityManager entityManager;
    @InitMethod
    @Override
    public void init() {
    }

    @Override
    public EngineDTO get(Long id) {
        return entityManager.get(id, EngineDTO.class).orElseGet(EngineDTO::new);
    }

    @Override
    public List<EngineDTO> getAll() {
        return entityManager.getAll(EngineDTO.class);
    }

    @Override
    public Long save(EngineDTO type) {
        return entityManager.save(type);
    }
}
