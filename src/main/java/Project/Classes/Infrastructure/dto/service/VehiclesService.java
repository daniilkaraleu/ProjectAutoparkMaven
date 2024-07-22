package Project.Classes.Infrastructure.dto.service;

import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.core.annotations.InitMethod;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.Service;
import Project.Classes.Infrastructure.dto.entity.OrderDTO;
import Project.Classes.Infrastructure.dto.entity.VehicleDTO;
import Project.Classes.UtilFiles.TechnicalSpecialist;
import Project.Classes.VehicleType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
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
        if (validateFields(vehicle)){
            return entityManager.save(vehicle);}
        return null;
    }
    public void deleteData(){
        entityManager.deleteData(VehicleDTO.class);
    }
    public void delete(long id){
        entityManager.delete(id,VehicleDTO.class);
    }


    private boolean validateFields(VehicleDTO vehicleDTO) {
        boolean result = true;

        Field []fields = vehicleDTO.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);

                Method method = VehicleDTO
                        .class
                        .getMethod("validate" +
                                field.getName().toUpperCase().charAt(0) +
                                field.getName().substring(1), TechnicalSpecialist.class);

                if (!(Boolean) method.invoke(null, field.get(vehicleDTO))){
                    return false;
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
