package ru.Kuzevanov_Alexander.NauJava.data.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.Kuzevanov_Alexander.NauJava.data.model.Classroom;

public interface ClassroomRepository extends CrudRepository<Classroom, Long> {
}
