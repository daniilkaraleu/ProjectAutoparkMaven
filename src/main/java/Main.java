import Project.Classes.*;
import Project.Classes.Collections.VehicleCollection;
import Project.Classes.Infrastructure.dto.service.RentsService;
import Project.Classes.Infrastructure.dto.service.VehiclesService;
import Project.Classes.interfaces.Fixer;
import Project.Classes.Infrastructure.core.Context;
import Project.Classes.Infrastructure.core.impl.ApplicationContext;
import Project.Classes.interfaces.Manager;
import Project.Classes.Infrastructure.dto.ConnectionFactory;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.impl.ConnectionFactoryImpl;
import Project.Classes.Infrastructure.dto.impl.EntityManagerImpl;
import lombok.SneakyThrows;

import java.util.*;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Map<Class<?>, Class<?>> interfaceToImplementation = new HashMap<>();

        interfaceToImplementation.put(Manager.class, CollectionManager.class);
        interfaceToImplementation.put(EntityManager.class, EntityManagerImpl.class);
        interfaceToImplementation.put(ConnectionFactory.class, ConnectionFactoryImpl.class);
        interfaceToImplementation.put(Context.class, ApplicationContext.class);
        interfaceToImplementation.put(Fixer.class, MechanicService.class);

        ApplicationContext context = new ApplicationContext("Project", interfaceToImplementation);
        VehicleCollection collection = context.getObject(CollectionManager.class).getVehicleCollection();
        VehicleGarageManager vehicleGarageManager = context.getObject(VehicleGarageManager.class);

        vehicleGarageManager.vehicleToGarage(context.getObject(VehiclesService.class), context.getObject(MechanicService.class), context.getObject(RentsService.class));

        Thread.sleep(200000);

    }
}