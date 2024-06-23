package Project.Classes.Infrastructure.core;

public interface Cache { //Обёртка над Map
    boolean contains(Class<?> clazz);
     <T> T get(Class<T> clazz);
     <T>void put(Class<T> clazz, T value);
 }
