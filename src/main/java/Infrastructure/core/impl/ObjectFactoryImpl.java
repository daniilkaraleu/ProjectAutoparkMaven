package Infrastructure.core.impl;

import Infrastructure.configurators.ObjectConfigurator;
import Infrastructure.core.Context;
import Infrastructure.core.ObjectFactory;
import Infrastructure.core.annotations.InitMethod;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectFactoryImpl implements ObjectFactory {
    private Context context;
    private List<ObjectConfigurator> objectConfigurators = new ArrayList<>();

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

    }

    @Override
    public <T> T createObject(Class<T> implementation) throws Exception{
        return initialize(implementation, configure(create(implementation)));
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
}
