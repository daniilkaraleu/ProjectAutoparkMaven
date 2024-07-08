package Project.Classes.Infrastructure.configurators.impl;

import Project.Classes.Infrastructure.configurators.ObjectConfigurator;
import Project.Classes.Infrastructure.core.Context;
import Project.Classes.Infrastructure.core.annotations.Autowired;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AutowiredObjectConfigurator implements ObjectConfigurator {
    public  AutowiredObjectConfigurator(){}

    @Override
    public void configure(Object object, Context context) {
        List<Field> fields = Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Autowired.class)).toList();

        fields.forEach(field -> {
                    field.setAccessible(true);
                    try {
                        field.set(object, context.getObject(field.getType()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
