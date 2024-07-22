package Project.Classes.Infrastructure.dto.service;

import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.core.annotations.InitMethod;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.Service;
import Project.Classes.Infrastructure.dto.entity.OrderDTO;
import Project.Classes.Infrastructure.dto.entity.RentDTO;

import java.util.List;

public class RentsService implements Service<RentDTO> {
    private static final String CSV = "rents";
    @Autowired
    EntityManager entityManager;

    public RentsService(){}

    @InitMethod
    public void init(){
    }
    public RentDTO get(Long id){
        return entityManager.get(id, RentDTO.class).orElseGet(RentDTO::new);
    }

    public List<RentDTO> getAll(){
        return entityManager.getAll(RentDTO.class);
    }

    public Long save(RentDTO rent){
        return entityManager.save(rent);
    }
    public void deleteData(){
        entityManager.deleteData(RentDTO.class);
    }
    public void delete(long id){
        entityManager.delete(id, RentDTO.class);
    }
}
