package senla.com.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericRepository<T, PK extends Serializable> {

    Optional<T> findById(PK id);

    List<T> findAll();

    void deleteById(PK id);

    void save(T entity);
}
