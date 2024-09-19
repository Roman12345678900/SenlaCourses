package senla.com.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository<T, PK extends Serializable> {
    T findById(PK id);

    List<T> findAll();

    void deleteById(PK id);

    void save(T entity);
}
