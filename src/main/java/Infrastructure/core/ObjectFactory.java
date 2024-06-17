package Infrastructure.core;

public interface ObjectFactory {
    <T> T createObject(Class<T> implementation) throws Exception; //создаем объект по умолчанию
}
