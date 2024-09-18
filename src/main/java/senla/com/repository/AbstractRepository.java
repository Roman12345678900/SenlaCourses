package senla.com.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
@RequiredArgsConstructor
public abstract class AbstractRepository<T, PK extends Serializable> implements GenericRepository<T, PK> {

    @PersistenceContext
    protected EntityManager entityManager;

    public abstract Class<T> getEntityclass();

    @Override
    public T findById(PK id) {
        return entityManager.find(getEntityclass(),id);
    }

    @Override
    public void save(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public List<T> findAll() {
        TypedQuery<T> query = entityManager.createQuery("select e from " + getEntityclass().getName() + " e", getEntityclass());

        return query.getResultList();
    }

    @Override
    public void deleteById(PK id) {
        T entity = findById(id);

        if (entity != null) {
            entityManager.remove(entity);
        }
    }
}
