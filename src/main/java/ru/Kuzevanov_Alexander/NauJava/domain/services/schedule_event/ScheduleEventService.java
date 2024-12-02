package ru.Kuzevanov_Alexander.NauJava.domain.services.schedule_event;

import ru.Kuzevanov_Alexander.NauJava.data.models.ScheduleEvent;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;

import java.util.List;

/**
 * Service interface for managing schedule events. This service is responsible for fetching and providing schedule events associated with specific groups.
 */
public interface ScheduleEventService {

    /**
     * Refreshes schedule events from an external source.  This method likely fetches data from an external API and updates the internal representation of schedule events.
     *  The specific groups whose events are refreshed might be determined by the implementation.
     * @throws ExternalApiException If an error occurs during communication with the external API.
     */
    void refresh() throws ExternalApiException;

    /**
     * Retrieves a list of schedule events for a given group ID.
     * @param groupId The ID of the group whose schedule events are to be retrieved.
     * @return A list of {@link ScheduleEvent} objects associated with the specified group ID. Returns an empty list if no events are found for the group.
     */
    List<ScheduleEvent> findByGroupId(Integer groupId);
}
