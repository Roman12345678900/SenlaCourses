package senla.com.repository;

import senla.com.entity.Classes;

import java.util.List;

public interface ClassesRepository {

    Classes findById(Long id);

    List<Classes> findAll();

    void save(Classes classes);

    void deleteById(Long id);
}
