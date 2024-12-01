package ru.Kuzevanov_Alexander.NauJava.domain.services.schedule;

import org.springframework.stereotype.Service;
import ru.Kuzevanov_Alexander.NauJava.data.external.ModelMapper;
import ru.Kuzevanov_Alexander.NauJava.data.models.Group;
import ru.Kuzevanov_Alexander.NauJava.domain.models.GroupSchedule;
import ru.Kuzevanov_Alexander.NauJava.data.models.ScheduleEvent;
import ru.Kuzevanov_Alexander.NauJava.data.external.models.GroupResponse;
import ru.Kuzevanov_Alexander.NauJava.data.external.models.ScheduleEventResponse;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.GroupRepository;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.ScheduleEventRepository;
import ru.Kuzevanov_Alexander.NauJava.data.external.ExternalApi;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.services.user.UserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ExternalApi externalApi;
    private final ScheduleEventRepository scheduleEventRepository;
    private final GroupRepository groupRepository;
    private final UserService userService;

    public ScheduleServiceImpl(ExternalApi externalApi, ScheduleEventRepository scheduleEventRepository, GroupRepository groupRepository, UserService userService) {
        this.externalApi = externalApi;
        this.scheduleEventRepository = scheduleEventRepository;
        this.groupRepository = groupRepository;
        this.userService = userService;
    }

    @Override
    public void refresh() throws ExternalApiException {
        refreshGroups();
        refreshEvents();
    }

    private void refreshGroups() throws ExternalApiException {
        List<GroupResponse> groupResponses = externalApi.getGroups();
        List<Group> groups = groupResponses.stream().map(ModelMapper::convertToEntity).toList();
        List<Group> allGroups = (List<Group>) groupRepository.findAll();
        Set<Integer> allGroupIds = allGroups.stream().map(Group::getId).collect(Collectors.toSet());
        List<Group> newGroups = groups.stream().filter(group -> !allGroupIds.contains(group.getId())).collect(Collectors.toList());
        groupRepository.saveAll(newGroups);
    }

    private void refreshEvents() throws ExternalApiException {
        List<Group> groups = (List<Group>) groupRepository.findAll();
        List<Integer> groupIds = groups.stream().map(Group::getId).toList();
        List<ScheduleEventResponse> scheduleEventResponses = externalApi.getScheduleEvents(groupIds);
        List<ScheduleEvent> scheduleEvents = scheduleEventResponses.stream().map(ModelMapper::convertToEntity).toList();
        scheduleEventRepository.saveAll(scheduleEvents);
        System.out.printf("%s schedule events successfully saved", scheduleEvents.size());
    }

    @Override
    public GroupSchedule getScheduleForCurrentUser() throws GroupNotFoundException {
        Integer groupId = userService.getCurrentUser().getGroupId();
        Group group = groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
        List<ScheduleEvent> events = scheduleEventRepository.findByGroupId(groupId);
        return new GroupSchedule(group, events);
    }
}
