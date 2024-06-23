package Project.Classes.Infrastructure.core;

import org.reflections.Reflections;

import java.util.Set;

public interface Scanner {
    <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type); //Возвращает все реализации класа
    Reflections getReflections();
}
