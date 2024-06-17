package Infrastructure.core.impl;

import Infrastructure.core.Scanner;
import org.reflections.Reflections;

import java.util.Set;

public class ScannerImpl implements Scanner {
    private final Reflections reflections;

    public ScannerImpl(String packageName) {
        reflections = new Reflections(packageName);
    }

    @Override
    public <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type) {
        Set<Class<? extends T>> set = reflections.getSubTypesOf(type);
        return set;
    }

    @Override
    public Reflections getReflections() {
        return reflections;
    }
}
