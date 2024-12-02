package ru.Kuzevanov_Alexander.NauJava.domain.services.schedule_event;

import org.springframework.stereotype.Service;
import ru.Kuzevanov_Alexander.NauJava.data.external.ModelMapper;
import ru.Kuzevanov_Alexander.NauJava.data.models.ScheduleEvent;
import ru.Kuzevanov_Alexander.NauJava.data.external.models.ScheduleEventResponse;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.ScheduleEventRepository;
import ru.Kuzevanov_Alexander.NauJava.data.external.ExternalApi;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;

import java.util.List;

/**
 * Service implementation for managing schedule events. This service fetches schedule events from an
 * external API and persists them in the database.
 */
@Service
public class ScheduleEventServiceImpl implements ScheduleEventService {

    private final ExternalApi externalApi;
    private final ScheduleEventRepository scheduleEventRepository;

    /**
     * Constructs a new ScheduleEventServiceImpl instance.
     *
     * @param externalApi             The external API client for fetching schedule event data.
     * @param scheduleEventRepository The repository for accessing and persisting schedule event data.
     */
    public ScheduleEventServiceImpl(ExternalApi externalApi, ScheduleEventRepository scheduleEventRepository) {
        this.externalApi = externalApi;
        this.scheduleEventRepository = scheduleEventRepository;
    }

    /**
     * {@inheritDoc}
     * <p>This implementation fetches schedule events from the external API for the given group IDs,
     * converts them to entities, and saves them to the database.  A log message indicating the
     * number of saved events is printed to the console.</p>
     */
    @Override
    public void refresh(List<Integer> groupIds) throws ExternalApiException {
        List<ScheduleEventResponse> scheduleEventResponses = externalApi.getScheduleEvents(groupIds);
        List<ScheduleEvent> scheduleEvents = scheduleEventResponses.stream().map(ModelMapper::convertToEntity).toList();
        scheduleEventRepository.saveAll(scheduleEvents);
        System.out.printf("%s schedule events successfully saved", scheduleEvents.size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ScheduleEvent> findByGroupId(Integer groupId) {
        return scheduleEventRepository.findByGroupId(groupId);
    }
}
