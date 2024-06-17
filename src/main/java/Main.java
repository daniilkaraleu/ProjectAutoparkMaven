import Classes.CollectionManager;
import Classes.Collections.AbstractAutoCollection;
import Classes.Collections.VehicleCollection;
import Infrastructure.config.Config;
import Infrastructure.config.impl.JavaConfig;
import Infrastructure.configurators.ObjectConfigurator;
import Infrastructure.configurators.impl.AutowiredObjectConfigurator;
import Infrastructure.core.Cache;
import Infrastructure.core.Context;
import Infrastructure.core.ObjectFactory;
import Infrastructure.core.impl.ApplicationContext;
import Infrastructure.core.impl.CacheImpl;
import Infrastructure.core.impl.ObjectFactoryImpl;
import Infrastructure.core.impl.ScannerImpl;
import Infrastructure.core.Scanner;
import Classes.interfaces.Manager;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Map<Class<?>, Class<?>> interfaceToImplementation = new HashMap<>();


        interfaceToImplementation.put(Manager.class, CollectionManager.class);


        ApplicationContext context = new ApplicationContext("Infrastructure.configurators.impl", interfaceToImplementation);

        context.getObject(CollectionManager.class).getVehicleCollection().display();

//        CollectionManager collectionsManager = new CollectionManager("rents","types", "vehicles");
//        collectionsManager.init("types", "vehicles");

//        collectionsManager.getVehicleCollection().sort(Vehicle::compareTo);
//
//        collectionsManager.getVehicleCollection().display();
//        collectionsManager.getTypesCollection().sort(Comparator.comparing(VehicleType::getType));
//
//        MechanicService mechanic = new MechanicService();
//
//        mechanic.detectAndRepair(collectionsManager.getVehicleCollection().getList().getFirst());

    }
}