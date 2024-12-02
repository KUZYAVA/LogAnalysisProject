package ru.Kuzevanov_Alexander.NauJava.domain.services.schedule_event;

import ru.Kuzevanov_Alexander.NauJava.data.models.ScheduleEvent;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;

import java.util.List;

/**
 * Service interface for managing schedule events. This service is responsible for fetching and
 * providing schedule events associated with specific groups.
 */
public interface ScheduleEventService {

    /**
     * Refreshes schedule events for the specified group IDs from an external source.
     *
     * @param groupIds A list of group IDs for which to refresh schedule events.
     * @throws ExternalApiException If an error occurs during communication with the external API.
     */
    void refresh(List<Integer> groupIds) throws ExternalApiException;

    /**
     * Retrieves a list of schedule events for a given group ID.
     *
     * @param groupId The ID of the group whose schedule events are to be retrieved.
     * @return A list of {@link ScheduleEvent} objects associated with the specified group ID.  Returns an empty list if no events are found or the group has no events.
     */
    List<ScheduleEvent> findByGroupId(Integer groupId);
}
