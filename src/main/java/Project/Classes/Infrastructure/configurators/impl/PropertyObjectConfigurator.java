package Project.Classes.Infrastructure.configurators.impl;

import Project.Classes.Infrastructure.configurators.ObjectConfigurator;
import Project.Classes.Infrastructure.core.Context;
import Project.Classes.Infrastructure.core.annotations.Property;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PropertyObjectConfigurator implements ObjectConfigurator {
    private final Map<String, String> properties;

    @SneakyThrows
    public PropertyObjectConfigurator() throws FileNotFoundException{
        URL path = this.getClass().getClassLoader().getResource("application/properties");
        if (path == null) {
            throw new FileNotFoundException(String.format("File '%s' not found", "application.properties"));
        }
        Stream<String> lines = new BufferedReader( new InputStreamReader(path.openStream())).lines();

        properties = lines.map(line -> line.split("="))
                .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
    }
     @Override
    public void configure(Object object, Context context) {
         Arrays.stream(object.getClass().getDeclaredFields())
                 .filter(field -> field.isAnnotationPresent(Property.class))
                 .forEach(field -> {
                     try {
                         field.setAccessible(true);
                         field.set(object, properties.containsKey(field.getAnnotation(Property.class).value())
                                 ? properties.get(field.getAnnotation(Property.class).value()) : properties.get(object.getClass().getName()));
                     } catch (IllegalAccessException e) {
                         throw new RuntimeException(e);
                     }
                 });
    }
}
