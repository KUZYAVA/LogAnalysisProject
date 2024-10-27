package ru.Kuzevanov_Alexander.NauJava.data.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.Kuzevanov_Alexander.NauJava.data.model.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
}
