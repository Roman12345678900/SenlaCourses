package senla.com.repository.implementation;

import org.springframework.stereotype.Repository;
import senla.com.entity.Classes;
import senla.com.repository.AbstractRepository;
import senla.com.repository.ClassesRepository;

@Repository
public class ClassesRepositoryImpl extends AbstractRepository<Classes> implements ClassesRepository {
}
