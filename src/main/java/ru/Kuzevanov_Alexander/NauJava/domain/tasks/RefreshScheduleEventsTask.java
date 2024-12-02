package ru.Kuzevanov_Alexander.NauJava.domain.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;
import ru.Kuzevanov_Alexander.NauJava.domain.services.group.GroupService;
import ru.Kuzevanov_Alexander.NauJava.domain.services.schedule_event.ScheduleEventService;

/**
 * A Spring scheduled task responsible for periodically refreshing schedule event data.
 * This task utilizes the {@link Scheduled} annotation to execute the refresh operation at a fixed rate (daily).
 */
@Component
public class RefreshScheduleEventsTask {

    private final ScheduleEventService scheduleEventService;
    private final GroupService groupService;

    private static final long ONE_DAY_RATE = 24 * 60 * 60 * 1000; // Milliseconds in a day

    /**
     * Constructs a new RefreshScheduleEventsTask instance.
     *
     * @param scheduleEventService The service used to refresh schedule event data.
     */
    public RefreshScheduleEventsTask(ScheduleEventService scheduleEventService, GroupService groupService) {
        this.scheduleEventService = scheduleEventService;
        this.groupService = groupService;
    }

    /**
     * Refreshes schedule event data from an external source. This method is annotated with {@link Scheduled} to run daily.
     *
     * @throws ExternalApiException If an error occurs during communication with the external API.
     */
    @Scheduled(fixedRate = ONE_DAY_RATE)
    public void refreshScheduleEvents() throws ExternalApiException {
        if (groupService.findAllIds().isEmpty()) {
            groupService.refresh();
        }
        scheduleEventService.refresh();
    }
}
