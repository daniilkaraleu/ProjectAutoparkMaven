package Project.Classes.Infrastructure.core;

import Project.Classes.Infrastructure.config.Config;

public interface Context {// для работы с контекстом
    <T> T getObject(Class<T> clazz) throws Exception;
    Config getConfig();
}
