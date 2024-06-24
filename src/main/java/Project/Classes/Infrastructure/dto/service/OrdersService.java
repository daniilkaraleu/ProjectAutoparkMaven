package Project.Classes.Infrastructure.dto.service;

import Project.Classes.Collections.ObjectCreator.CreateRents;
import Project.Classes.Collections.ObjectCreator.ParseVehicleFromFileToDB;
import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.core.annotations.InitMethod;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.Service;
import Project.Classes.Infrastructure.dto.entity.Orders;
import Project.Classes.Infrastructure.dto.entity.Rents;

import java.util.List;

public class OrdersService implements Service<Orders> {
    @Autowired
    EntityManager entityManager;

    public OrdersService(){}

    @InitMethod
    public void init(){
    }
    public Orders get(Long id){
        return entityManager.get(id, Orders.class).orElseGet(Orders::new);
    }

    public List<Orders> getAll(){
        return entityManager.getAll(Orders.class);
    }

    public Long save(Orders orders){
        return entityManager.save(orders);
    }

    public void deleteData(Class<?> clazz){
        entityManager.deleteData(clazz);
    }
}
