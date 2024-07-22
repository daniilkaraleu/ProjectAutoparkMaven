package Project.servlets.utils;

import Project.Classes.CollectionManager;
import Project.Classes.Infrastructure.core.Context;
import Project.Classes.Infrastructure.core.impl.ApplicationContext;
import Project.Classes.Infrastructure.dto.ConnectionFactory;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.impl.ConnectionFactoryImpl;
import Project.Classes.Infrastructure.dto.impl.EntityManagerImpl;
import Project.Classes.MechanicService;
import Project.Classes.interfaces.Fixer;
import Project.Classes.interfaces.Manager;

import java.util.HashMap;
import java.util.Map;

public class InterfaceToImplementation {
    public static Map<Class<?>, Class<?>> interfaceToImplementation = new HashMap<>();

    private InterfaceToImplementation(){}

    static {
        interfaceToImplementation.put(Manager.class, CollectionManager.class);
        interfaceToImplementation.put(EntityManager.class, EntityManagerImpl.class);
        interfaceToImplementation.put(ConnectionFactory.class, ConnectionFactoryImpl.class);
        interfaceToImplementation.put(Context.class, ApplicationContext.class);
        interfaceToImplementation.put(Fixer.class, MechanicService.class);
    }
}
