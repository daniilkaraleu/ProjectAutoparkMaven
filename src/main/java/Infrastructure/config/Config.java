package Infrastructure.config;

import Infrastructure.core.Scanner;

public interface Config { // настройка контекста
    <T> Class<T> getImplementation(Class<T> target);
    Scanner getScanner();
}
