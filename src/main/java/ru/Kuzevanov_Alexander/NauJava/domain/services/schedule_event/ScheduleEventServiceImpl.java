package ru.Kuzevanov_Alexander.NauJava.domain.services.schedule_event;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.Kuzevanov_Alexander.NauJava.data.external.ModelMapper;
import ru.Kuzevanov_Alexander.NauJava.data.models.ScheduleEvent;
import ru.Kuzevanov_Alexander.NauJava.data.external.models.ScheduleEventResponse;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.ScheduleEventRepository;
import ru.Kuzevanov_Alexander.NauJava.data.external.ExternalApi;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;
import ru.Kuzevanov_Alexander.NauJava.domain.services.group.GroupService;

import java.util.List;

/**
 * Service implementation for managing schedule events. This service fetches schedule events from an external API and persists them in the database.
 */
@Service
public class ScheduleEventServiceImpl implements ScheduleEventService {

    private final ExternalApi externalApi;
    private final ScheduleEventRepository scheduleEventRepository;
    private final GroupService groupService;

    /**
     * Constructs a new ScheduleEventServiceImpl instance.
     *
     * @param externalApi             The external API client for fetching schedule event data.
     * @param scheduleEventRepository The repository for accessing and persisting schedule event data.
     * @param groupService            The service for retrieving group IDs.
     */
    public ScheduleEventServiceImpl(ExternalApi externalApi, ScheduleEventRepository scheduleEventRepository, GroupService groupService) {
        this.externalApi = externalApi;
        this.scheduleEventRepository = scheduleEventRepository;
        this.groupService = groupService;
    }

    /**
     * {@inheritDoc}
     * <p>This implementation fetches schedule events from the external API for all groups, converts them to entities, and replaces the existing database entries.
     * A log message indicating the number of saved events is printed to the console.</p>
     */
    @Override
    public void refresh() throws ExternalApiException {
        List<Integer> groupIds = groupService.findAllIds();
        List<ScheduleEventResponse> scheduleEventResponses = externalApi.getScheduleEvents(groupIds);
        List<ScheduleEvent> scheduleEvents = scheduleEventResponses.stream().map(ModelMapper::convertToEntity).toList();
        replaceAll(scheduleEvents);
        System.out.printf("%d schedule events successfully saved%n", scheduleEvents.size());
    }

    /**
     * Transactionally deletes all existing schedule events and saves the provided list.  This ensures atomicity of the operation.
     *
     * @param scheduleEvents The list of schedule events to save.
     */
    @Transactional
    private void replaceAll(List<ScheduleEvent> scheduleEvents) {
        scheduleEventRepository.deleteAll();
        scheduleEventRepository.saveAll(scheduleEvents);
    }

    /**
     * {@inheritDoc}
     * This method retrieves all schedule events associated with a specific group ID from the database.
     *
     * @param groupId The ID of the group.
     * @return A list of ScheduleEvent objects associated with the groupId.  Returns an empty list if no events are found for the given group ID.
     */
    @Override
    public List<ScheduleEvent> findByGroupId(Integer groupId) {
        return scheduleEventRepository.findByGroupId(groupId);
    }
}
