package Project.Classes.Infrastructure.dto.service;

import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.core.annotations.InitMethod;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.Service;
import Project.Classes.Infrastructure.dto.entity.OrderDTO;

import java.util.List;

public class OrdersService implements Service<OrderDTO> {
    @Autowired
    EntityManager entityManager;

    public OrdersService(){}

    @InitMethod
    public void init(){
    }
    public OrderDTO get(Long id){
        return entityManager.get(id, OrderDTO.class).orElseGet(OrderDTO::new);
    }

    public List<OrderDTO> getAll(){
        return entityManager.getAll(OrderDTO.class);
    }

    public Long save(OrderDTO orders){
        return entityManager.save(orders);
    }

    public void deleteData(){
        entityManager.deleteData(OrderDTO.class);
    }
    public void delete(long id){
        entityManager.delete(id, OrderDTO.class);
    }
}
