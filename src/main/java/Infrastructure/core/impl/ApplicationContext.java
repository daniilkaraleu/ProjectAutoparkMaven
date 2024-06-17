package Infrastructure.core.impl;

import Infrastructure.config.Config;
import Infrastructure.config.impl.JavaConfig;
import Infrastructure.core.Cache;
import Infrastructure.core.Context;
import Infrastructure.core.ObjectFactory;

import java.util.Map;

public class ApplicationContext implements Context {
    private final Config config;
    private final Cache cache;
    private final ObjectFactory factory;

    public ApplicationContext(String packageToScan, Map<Class<?>, Class<?>> interfaceToImplementation) {
        this.config = new JavaConfig(new ScannerImpl(packageToScan), interfaceToImplementation);
        this.cache = new CacheImpl();
        this.factory = new ObjectFactoryImpl(this);
    }

    @Override
    public <T> T getObject(Class<T> clazz) throws Exception {
        if (cache.contains(clazz)) {
            return cache.get(clazz);
        }

        cache.put(clazz, clazz.isInterface() ? factory.createObject(config.getImplementation(clazz)) : factory.createObject(clazz));

        return cache.get(clazz);
    }

    @Override
    public Config getConfig() {
        return config;
    }
}
