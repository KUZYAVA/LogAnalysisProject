package ru.Kuzevanov_Alexander.NauJava.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Kuzevanov_Alexander.NauJava.data.model.Report;

@RepositoryRestResource(path = "reports")
public interface ReportRepository extends CrudRepository<Report, Long> {
}
