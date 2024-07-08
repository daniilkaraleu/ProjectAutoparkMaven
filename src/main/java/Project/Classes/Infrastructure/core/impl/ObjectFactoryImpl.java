package Project.Classes.Infrastructure.core.impl;

import Project.Classes.Infrastructure.configurators.ObjectConfigurator;
import Project.Classes.Infrastructure.configurators.ProxyConfigurator;
import Project.Classes.Infrastructure.core.Context;
import Project.Classes.Infrastructure.core.ObjectFactory;
import Project.Classes.Infrastructure.core.annotations.InitMethod;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectFactoryImpl implements ObjectFactory {
    private Context context;
    private List<ObjectConfigurator> objectConfigurators = new ArrayList<>();
    private List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactoryImpl(Context context) {
        this.context = context;

        objectConfigurators = context
                .getConfig()
                .getScanner()
                .getSubTypesOf(ObjectConfigurator.class)
                .stream()
                .map(clazz -> {
                    try {
                        return clazz.getConstructor().newInstance();
                    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

        proxyConfigurators = context
                .getConfig()
                .getScanner()
                .getSubTypesOf(ProxyConfigurator.class)
                .stream()
                .map(clazz -> {
                    try {
                        return clazz.getConstructor().newInstance();
                    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

    }

    @Override
    public <T> T createObject(Class<T> implementation) throws Exception{

        T instance = makeProxy(implementation, create(implementation));
        instance = configure(instance);

        return initialize(implementation, instance);
    }

    private <T> T create(Class<T> implementation) throws Exception{
        return implementation.getConstructor().newInstance();
    }

    private <T> T configure( T instance) {
        objectConfigurators.forEach(config -> config.configure(instance, context));

        return instance;
    }

    @SneakyThrows
    private <T> T initialize(Class<T> implementation, T instance) {
        Method []methods = implementation.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(InitMethod.class))
                method.invoke(instance);
        }

        return instance;
    }

    private <T> T makeProxy(Class<T> implementation, T instance){
        for (ProxyConfigurator proxyConfigurator : proxyConfigurators) {
            instance = proxyConfigurator.makeProxy(instance, implementation, context);
        }
        return instance;
    }
}
