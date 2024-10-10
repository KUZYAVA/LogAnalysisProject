package ru.Kuzevanov_Alexander.NauJava.data.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.Kuzevanov_Alexander.NauJava.data.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
