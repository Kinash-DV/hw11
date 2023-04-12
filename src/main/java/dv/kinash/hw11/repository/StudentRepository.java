package dv.kinash.hw11.repository;

import dv.kinash.hw11.model.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
