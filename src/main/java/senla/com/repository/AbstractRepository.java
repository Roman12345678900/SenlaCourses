package senla.com.repository;

import org.springframework.stereotype.Repository;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AbstractRepository<T> {
    private final List<T> objects = new ArrayList<>();
    private long idCounter = 1;

    public T findById(Long id) {
        return objects.stream()
                .filter(obj -> {
                    try {
                        return id.equals(getId(obj));
                    } catch (Exception e) {
                        throw new RuntimeException("Error getting ID from object", e);
                    }
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Object with id " + id + " not found"));
    }

    public List<T> findAll() {
        return new ArrayList<>(objects);
    }

    public void save(T object) {
        try {
            Long id = getId(object);
            if (id == null || !objects.contains(object)) {
                if (id == null) {
                    setId(object, idCounter++);
                }
                objects.add(object);
            } else {
                int index = objects.indexOf(object);
                objects.set(index, object);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error setting ID for object", e);
        }
    }

    public void deleteById(Long id) {
        objects.removeIf(obj -> {
            try {
                return id.equals(getId(obj));
            } catch (Exception e) {
                throw new RuntimeException("Error getting ID from object", e);
            }
        });
    }

    private Long getId(T object) throws Exception {
        Method getIdMethod = object.getClass().getMethod("getId");
        return (Long) getIdMethod.invoke(object);
    }

    private void setId(T object, Long id) throws Exception {
        Method setIdMethod = object.getClass().getMethod("setId", Long.class);
        setIdMethod.invoke(object, id);
    }
}
