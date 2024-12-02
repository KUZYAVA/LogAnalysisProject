package ru.Kuzevanov_Alexander.NauJava.domain.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;
import ru.Kuzevanov_Alexander.NauJava.domain.services.group.GroupService;
import ru.Kuzevanov_Alexander.NauJava.domain.services.schedule_event.ScheduleEventService;

import java.util.List;

/**
 * A Spring scheduled task responsible for periodically refreshing group and schedule event data.
 * This task utilizes the {@link Scheduled} annotation to execute the refresh operation at a fixed
 * rate.
 */
@Component
public class RefreshTask {

    private final GroupService groupService;
    private final ScheduleEventService scheduleEventService;

    private static final long ONE_DAY_RATE = 24 * 60 * 60 * 1000; // Milliseconds in a day

    /**
     * Constructs a new RefreshTask instance.
     *
     * @param groupService         The service for managing groups.
     * @param scheduleEventService The service for managing schedule events.
     */
    public RefreshTask(GroupService groupService, ScheduleEventService scheduleEventService) {
        this.groupService = groupService;
        this.scheduleEventService = scheduleEventService;
    }

    /**
     * Refreshes group and schedule event data. This method is annotated with {@link Scheduled}
     * to run daily.  It first refreshes group data and then refreshes schedule event data for all
     * existing groups.
     *
     * @throws ExternalApiException If an error occurs during communication with the external API.
     */
    @Scheduled(fixedRate = ONE_DAY_RATE)
    public void refresh() throws ExternalApiException {
        groupService.refresh();
        List<Integer> groupIds = groupService.findAllIds();
        scheduleEventService.refresh(groupIds);
    }
}
