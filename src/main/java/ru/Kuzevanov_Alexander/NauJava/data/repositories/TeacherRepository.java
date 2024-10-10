package ru.Kuzevanov_Alexander.NauJava.data.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.Kuzevanov_Alexander.NauJava.data.model.Teacher;

import java.util.List;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    List<Teacher> findByEmail(String email);
}
