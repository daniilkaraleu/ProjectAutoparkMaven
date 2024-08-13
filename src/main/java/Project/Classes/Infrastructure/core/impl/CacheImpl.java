package Project.Classes.Infrastructure.core.impl;

import Project.Classes.Infrastructure.core.Cache;

import java.util.HashMap;
import java.util.Map;

public class CacheImpl implements Cache {
    private final Map<String, Object> cache;

    public CacheImpl() {
        cache = new HashMap<>();
    }

    @Override
    public boolean contains(Class<?> clazz) {
        return cache.containsKey(clazz.getName());
    }

    @Override
    public <T> T get(Class<T> clazz) {
        return clazz.cast(cache.get(clazz.getName()));
    }

    @Override
    public <T> void put(Class<T> clazz, T value) {
        if (clazz == null)
            throw new NullPointerException();
        cache.put(clazz.getName(), value);
    }
}
