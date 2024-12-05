package ru.Kuzevanov_Alexander.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Kuzevanov_Alexander.NauJava.data.models.ScheduleEvent;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.ScheduleEventRepository;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;
import ru.Kuzevanov_Alexander.NauJava.domain.services.schedule_event.ScheduleEventService;

import java.util.List;

/**
 * Test class for the {@link ScheduleEventService}. This class contains unit tests to verify the functionality of the {@link ScheduleEventService} methods.
 */
@SpringBootTest
public class ScheduleEventServiceTest {

    private final ScheduleEventService scheduleEventService;
    private final ScheduleEventRepository scheduleEventRepository;

    /**
     * Constructs a new ScheduleEventServiceTest instance.
     *
     * @param scheduleEventService    The {@link ScheduleEventService} to test.
     * @param scheduleEventRepository The {@link ScheduleEventRepository} to use in tests.
     */
    @Autowired
    public ScheduleEventServiceTest(ScheduleEventService scheduleEventService, ScheduleEventRepository scheduleEventRepository) {
        this.scheduleEventService = scheduleEventService;
        this.scheduleEventRepository = scheduleEventRepository;
    }

    /**
     * Tests the refresh method of the {@link ScheduleEventService}. This test verifies that the refresh method populates the database with schedule event data and that the database is not empty after the refresh.
     * This test assumes that the external API is available and returns data.  Failure may indicate an issue with the API or network connectivity.
     *
     * @throws ExternalApiException If an error occurs during the refresh operation.
     */
    @Test
    void refresh() throws ExternalApiException {
        scheduleEventService.refresh();
        List<ScheduleEvent> scheduleEvents = (List<ScheduleEvent>) scheduleEventRepository.findAll();
        Assertions.assertFalse(scheduleEvents.isEmpty(), "Expected scheduleEvents to not be empty after refresh");
    }

    /**
     * Tests the findByGroupId method of the {@link ScheduleEventService}. This test verifies that the method correctly retrieves a list of {@link ScheduleEvent} objects for a given group ID.
     * The test first retrieves all schedule events from the database.  If the database is empty, the test will fail.  This implies that the `refresh()` method must be called prior to this test.
     * It then selects a groupId from the retrieved events and checks if `findByGroupId` returns at least one event with that ID.
     */
    @Test
    void findByGroupId() {
        List<ScheduleEvent> scheduleEvents = (List<ScheduleEvent>) scheduleEventRepository.findAll();
        Integer groupId = scheduleEvents.getFirst().getGroupId();
        List<Integer> groupIds = scheduleEventService.findByGroupId(groupId).stream().map(ScheduleEvent::getGroupId).toList();
        Assertions.assertTrue(groupIds.contains(groupId), "Expected at least one ScheduleEvent with the given group ID");
    }
}
