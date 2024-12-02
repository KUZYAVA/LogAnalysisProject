package ru.Kuzevanov_Alexander.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Kuzevanov_Alexander.NauJava.data.models.ScheduleEvent;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.ScheduleEventRepository;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;
import ru.Kuzevanov_Alexander.NauJava.domain.services.group.GroupService;
import ru.Kuzevanov_Alexander.NauJava.domain.services.schedule_event.ScheduleEventService;

import java.util.List;

@SpringBootTest
public class ScheduleEventServiceTest {

    private final ScheduleEventService scheduleEventService;
    private final ScheduleEventRepository scheduleEventRepository;
    private final GroupService groupService;

    @Autowired
    public ScheduleEventServiceTest(
            ScheduleEventService scheduleEventService,
            ScheduleEventRepository scheduleEventRepository,
            GroupService groupService
    ) {
        this.scheduleEventService = scheduleEventService;
        this.scheduleEventRepository = scheduleEventRepository;
        this.groupService = groupService;
    }

    @Test
    void refresh() throws ExternalApiException {
        scheduleEventRepository.deleteAll();
        List<Integer> groupIds = groupService.findAllIds();
        scheduleEventService.refresh(groupIds);
        List<ScheduleEvent> scheduleEvents = (List<ScheduleEvent>) scheduleEventRepository.findAll();
        Assertions.assertFalse(scheduleEvents.isEmpty());
    }

    @Test
    void findByGroupId() {
        List<ScheduleEvent> scheduleEvents = (List<ScheduleEvent>) scheduleEventRepository.findAll();
        Integer groupId = scheduleEvents.getFirst().getGroupId();
        List<Integer> groupIds = scheduleEventService
                .findByGroupId(groupId)
                .stream()
                .map(ScheduleEvent::getGroupId)
                .toList();
        Assertions.assertTrue(groupIds.contains(groupId));
    }
}
