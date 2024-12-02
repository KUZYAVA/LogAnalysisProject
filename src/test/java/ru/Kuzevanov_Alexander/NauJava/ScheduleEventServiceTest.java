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

/**
 * Test class for the {@link ScheduleEventService}. This class contains unit tests to verify the
 * functionality of the {@link ScheduleEventService} methods, including `refresh` and `findByGroupId`.
 */
@SpringBootTest
public class ScheduleEventServiceTest {

    private final ScheduleEventService scheduleEventService;
    private final ScheduleEventRepository scheduleEventRepository;
    private final GroupService groupService;

    /**
     * Constructs a new ScheduleEventServiceTest instance.
     *
     * @param scheduleEventService    The {@link ScheduleEventService} to test.
     * @param scheduleEventRepository The {@link ScheduleEventRepository} to use in tests.
     * @param groupService            The {@link GroupService} to use for retrieving group IDs.
     */
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

    /**
     * Tests the `refresh` method of the {@link ScheduleEventService}. This test verifies that the
     * `refresh` method populates the database with schedule event data and that the database is not
     * empty after the refresh. It uses the {@link GroupService} to obtain a list of group IDs to refresh.
     *
     * @throws ExternalApiException If an error occurs during the refresh operation.
     */
    @Test
    void refresh() throws ExternalApiException {
        scheduleEventRepository.deleteAll();
        List<Integer> groupIds = groupService.findAllIds();
        scheduleEventService.refresh(groupIds);
        List<ScheduleEvent> scheduleEvents = (List<ScheduleEvent>) scheduleEventRepository.findAll();
        Assertions.assertFalse(scheduleEvents.isEmpty(), "Expected scheduleEvents to not be empty after refresh");
    }

    /**
     * Tests the `findByGroupId` method of the {@link ScheduleEventService}. This test verifies that
     * the method correctly retrieves a list of {@link ScheduleEvent} objects for a given group ID and
     * that the list contains at least one event with the specified group ID. It retrieves a group ID
     * from an existing event in the database.
     */
    @Test
    void findByGroupId() {
        List<ScheduleEvent> scheduleEvents = (List<ScheduleEvent>) scheduleEventRepository.findAll();
        Integer groupId = scheduleEvents.getFirst().getGroupId();
        List<Integer> groupIds = scheduleEventService.findByGroupId(groupId).stream().map(ScheduleEvent::getGroupId).toList();
        Assertions.assertTrue(groupIds.contains(groupId), "Expected at least one ScheduleEvent with the given group ID");
    }
}
