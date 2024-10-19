package ru.Kuzevanov_Alexander.NauJava.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Kuzevanov_Alexander.NauJava.data.model.Student;

@RepositoryRestResource(path = "students")
public interface StudentRepository extends CrudRepository<Student, Long> {
}
