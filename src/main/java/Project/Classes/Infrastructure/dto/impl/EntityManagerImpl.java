package Project.Classes.Infrastructure.dto.impl;

import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.PostgreDataBase;

import java.util.List;
import java.util.Optional;

public class EntityManagerImpl implements EntityManager {
    @Autowired
    private PostgreDataBase dataBaseService;

    public EntityManagerImpl(){}

    @Override
    public <T> Optional<T> get(Long id, Class<T> clazz) {
        return dataBaseService.get(id, clazz);
    }

    @Override
    public <T> List<T> getAll(Class<T> clazz) {
        return dataBaseService.getAll(clazz);
    }

    @Override
    public Long save(Object object) {
        return dataBaseService.save(object);
    }

    @Override
    public void creteSeq(String seqName) {
        dataBaseService.createSeq(seqName);
    }
}
