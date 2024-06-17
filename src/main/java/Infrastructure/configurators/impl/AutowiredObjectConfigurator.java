package Infrastructure.configurators.impl;

import Infrastructure.configurators.ObjectConfigurator;
import Infrastructure.core.Context;
import Infrastructure.core.annotations.Autowired;
import java.util.Arrays;

public class AutowiredObjectConfigurator implements ObjectConfigurator {
    public  AutowiredObjectConfigurator(){}

    @Override
    public void configure(Object object, Context context) {
        Arrays
                .stream(object.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Autowired.class))
                .map(field -> {
                    try {
                        return context.getObject(field.getType());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).forEach(value -> Arrays
                        .stream(object.getClass().getDeclaredFields())
                        .forEach(field -> {
                    if (field.getType().getName().equals(value.getClass().getName())) {
                        try {
                            field.setAccessible(true);
                            field.set(object, value);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }));
    }
}
