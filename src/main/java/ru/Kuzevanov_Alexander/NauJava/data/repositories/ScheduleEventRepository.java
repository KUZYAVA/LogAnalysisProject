package ru.Kuzevanov_Alexander.NauJava.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Kuzevanov_Alexander.NauJava.data.models.ScheduleEvent;

import java.util.List;

@RepositoryRestResource(path = "schedule_events")
public interface ScheduleEventRepository extends CrudRepository<ScheduleEvent, String> {

    List<ScheduleEvent> findByGroupId(Integer groupId);
}
