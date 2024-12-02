package ru.Kuzevanov_Alexander.NauJava.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Kuzevanov_Alexander.NauJava.data.models.ScheduleEvent;

import java.util.List;

/**
 * Repository for managing {@link ScheduleEvent} entities.
 * This interface extends Spring Data's {@link CrudRepository} providing basic CRUD operations.
 * It also uses {@link RepositoryRestResource} to expose the repository's functionality via REST.
 */
@RepositoryRestResource(path = "schedule_events")
public interface ScheduleEventRepository extends CrudRepository<ScheduleEvent, String> {

    /**
     * Finds a list of {@link ScheduleEvent} entities associated with a given group ID.
     *
     * @param groupId The ID of the group to filter by.
     * @return A list of {@link ScheduleEvent} objects belonging to the specified group.  Returns an empty list if no events are found for the given group ID.
     */
    List<ScheduleEvent> findByGroupId(Integer groupId);
}
