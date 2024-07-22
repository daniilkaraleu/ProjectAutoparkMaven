package Project.Classes.Infrastructure.dto;

import Project.Classes.interfaces.Manager;

import java.util.List;
import java.util.Optional;

public interface EntityManager extends Manager {
    <T>Optional<T> get(Long id, Class<T> clazz);
    <T> List<T> getAll(Class<T> clazz);

    Long save(Object object);
    void creteSeq(String seqName);
    void deleteData(Class<?> clazz);
    void delete(long id, Class<?> clazz);
}

