package Project.Classes.Infrastructure.dto;

import Project.Classes.Infrastructure.core.Cache;
import Project.Classes.Infrastructure.core.annotations.InitMethod;
import Project.Classes.Infrastructure.dto.entity.OrderDTO;

import java.util.List;

public interface Service<T> {
    @InitMethod
    public void init();
    public T get(Long id);

    public  List<T> getAll();

    public Long save(T type);

    public void deleteData();
    public void delete(long id);

}
