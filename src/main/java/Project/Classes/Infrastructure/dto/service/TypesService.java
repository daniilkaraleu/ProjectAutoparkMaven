package Project.Classes.Infrastructure.dto.service;

import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.core.annotations.InitMethod;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.Service;
import Project.Classes.Infrastructure.dto.entity.OrderDTO;
import Project.Classes.Infrastructure.dto.entity.TypeDTO;

import java.util.List;

public class TypesService implements Service<TypeDTO> {
    private static final String CSV = "types";

    public TypesService(){}

    @Autowired
    EntityManager entityManager;

    @InitMethod
    public void init(){
    }
    public TypeDTO get(Long id){
        return entityManager.get(id, TypeDTO.class).orElseGet(TypeDTO::new);
    }

    public List<TypeDTO> getAll(){
        return entityManager.getAll(TypeDTO.class);
    }

    public Long save(TypeDTO type){
        return entityManager.save(type);
    }
    public void deleteData(){
        entityManager.deleteData(TypeDTO.class);
    }
    public void delete(long id){
        entityManager.delete(id, TypeDTO.class);
    }
}
