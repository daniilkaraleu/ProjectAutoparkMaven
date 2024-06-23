package Project.Classes.Infrastructure.config.impl;

import Project.Classes.Infrastructure.config.Config;
import Project.Classes.Infrastructure.core.Scanner;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Set;

@AllArgsConstructor
public class JavaConfig implements Config {
    private Scanner scanner;
    private Map<Class<?>,Class<?>> interfaceToImplement;

    @Override
    public <T> Class<T> getImplementation(Class<T> target) throws RuntimeException{

        Set<Class<? extends T>> implementation= scanner.getSubTypesOf(target);

        if (implementation.size() != 1)
            throw new RuntimeException("There's 0 or more than one implementations of the interface");

        return (Class<T>) interfaceToImplement.get(target);
    }

    @Override
    public Scanner getScanner() {
        return scanner;
    }
}
