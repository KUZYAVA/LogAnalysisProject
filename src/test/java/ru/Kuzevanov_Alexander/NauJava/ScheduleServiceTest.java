package ru.Kuzevanov_Alexander.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Kuzevanov_Alexander.NauJava.data.models.Group;
import ru.Kuzevanov_Alexander.NauJava.data.models.ScheduleEvent;
import ru.Kuzevanov_Alexander.NauJava.data.models.User;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.GroupRepository;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.ScheduleEventRepository;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.UserRepository;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;
import ru.Kuzevanov_Alexander.NauJava.domain.models.GroupSchedule;
import ru.Kuzevanov_Alexander.NauJava.domain.services.schedule.ScheduleService;

import java.util.List;

@SpringBootTest
public class ScheduleServiceTest {

    private final ScheduleService scheduleService;
    private final ScheduleEventRepository scheduleEventRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Autowired
    public ScheduleServiceTest(ScheduleService scheduleService, ScheduleEventRepository scheduleEventRepository, GroupRepository groupRepository, UserRepository userRepository) {
        this.scheduleService = scheduleService;
        this.scheduleEventRepository = scheduleEventRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Test
    void refresh() throws ExternalApiException {
        groupRepository.deleteAll();
        scheduleEventRepository.deleteAll();
        scheduleService.refresh();
        List<Group> groups = (List<Group>) groupRepository.findAll();
        Assertions.assertFalse(groups.isEmpty());
        List<ScheduleEvent> scheduleEvents = (List<ScheduleEvent>) scheduleEventRepository.findAll();
        Assertions.assertFalse(scheduleEvents.isEmpty());
    }

    @Test
    void getGroupSchedule() {
        List<User> users = (List<User>) userRepository.findAll();
        User user = users.getFirst();
        Group group = groupRepository.findById(user.getGroupId()).orElse(null);
        List<ScheduleEvent> events = scheduleEventRepository.findByGroupId(user.getGroupId());
        GroupSchedule groupSchedule = new GroupSchedule(group, events);
        Assertions.assertNotNull(groupSchedule.group());
        Assertions.assertFalse(groupSchedule.events().isEmpty());
    }
}
