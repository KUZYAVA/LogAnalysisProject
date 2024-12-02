package ru.Kuzevanov_Alexander.NauJava.domain.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;
import ru.Kuzevanov_Alexander.NauJava.domain.services.group.GroupService;
import ru.Kuzevanov_Alexander.NauJava.domain.services.schedule_event.ScheduleEventService;

import java.util.List;

@Component
public class RefreshTask {

    private final GroupService groupService;
    private final ScheduleEventService scheduleEventService;

    private static final long ONE_DAY_RATE = 24 * 60 * 60 * 1000;

    public RefreshTask(GroupService groupService, ScheduleEventService scheduleEventService) {
        this.groupService = groupService;
        this.scheduleEventService = scheduleEventService;
    }

    @Scheduled(fixedRate = ONE_DAY_RATE)
    public void refresh() throws ExternalApiException {
        groupService.refresh();
        List<Integer> groupIds = groupService.findAllIds();
        scheduleEventService.refresh(groupIds);
    }
}
