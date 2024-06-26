package Project.Classes.Infrastructure.configurators;

import Project.Classes.Infrastructure.core.Context;

public interface ProxyConfigurator {
    <T> T makeProxy(T obj, Class<T> implementation, Context context);
}
