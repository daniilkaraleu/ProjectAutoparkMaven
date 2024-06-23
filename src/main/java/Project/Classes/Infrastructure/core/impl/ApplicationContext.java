package Project.Classes.Infrastructure.core.impl;

import Project.Classes.Infrastructure.config.Config;
import Project.Classes.Infrastructure.config.impl.JavaConfig;
import Project.Classes.Infrastructure.core.Cache;
import Project.Classes.Infrastructure.core.Context;
import Project.Classes.Infrastructure.core.ObjectFactory;

import java.util.Map;

public class ApplicationContext implements Context {
    private Config config;
    private Cache cache;
    private ObjectFactory factory;
    public ApplicationContext(){}

    public ApplicationContext(String packageToScan, Map<Class<?>, Class<?>> interfaceToImplementation) {
        this.config = new JavaConfig(new ScannerImpl(packageToScan), interfaceToImplementation);
        this.cache = new CacheImpl();
        this.factory = new ObjectFactoryImpl(this);

        cache.put(Context.class, this);
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
