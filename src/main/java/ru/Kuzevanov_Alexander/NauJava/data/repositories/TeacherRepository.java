package ru.Kuzevanov_Alexander.NauJava.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Kuzevanov_Alexander.NauJava.data.model.Teacher;

import java.util.List;

@RepositoryRestResource(path = "teachers")
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    List<Teacher> findByEmail(String email);
}
