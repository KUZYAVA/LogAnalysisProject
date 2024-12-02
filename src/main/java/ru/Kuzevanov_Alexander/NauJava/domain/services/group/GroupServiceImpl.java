package ru.Kuzevanov_Alexander.NauJava.domain.services.group;

import org.springframework.stereotype.Service;
import ru.Kuzevanov_Alexander.NauJava.data.external.ExternalApi;
import ru.Kuzevanov_Alexander.NauJava.data.external.ModelMapper;
import ru.Kuzevanov_Alexander.NauJava.data.external.models.GroupResponse;
import ru.Kuzevanov_Alexander.NauJava.data.models.Group;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.GroupRepository;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private final ExternalApi externalApi;
    private final GroupRepository groupRepository;

    public GroupServiceImpl(ExternalApi externalApi, GroupRepository groupRepository) {
        this.externalApi = externalApi;
        this.groupRepository = groupRepository;
    }

    @Override
    public void refresh() throws ExternalApiException {
        List<GroupResponse> groupResponses = externalApi.getGroups();
        List<Group> groups = groupResponses.stream().map(ModelMapper::convertToEntity).toList();
        List<Group> allGroups = (List<Group>) groupRepository.findAll();
        Set<Integer> allGroupIds = allGroups.stream().map(Group::getId).collect(Collectors.toSet());
        List<Group> newGroups = groups.stream().filter(group -> !allGroupIds.contains(group.getId())).toList();
        groupRepository.saveAll(newGroups);
    }

    @Override
    public List<Integer> findAllIds() {
        List<Group> groups = (List<Group>) groupRepository.findAll();
        return groups.stream().map(Group::getId).toList();
    }

    @Override
    public Group findById(Integer id) throws GroupNotFoundException {
        return groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
    }

    @Override
    public Group findByTitle(String title) throws GroupNotFoundException {
        return groupRepository.findByTitle(title).orElseThrow(GroupNotFoundException::new);
    }

    @Override
    public void changeVisibility(String title) throws GroupNotFoundException {
        Group group = findByTitle(title);
        group.setHiddenByAdmin(!group.getHiddenByAdmin());
        groupRepository.save(group);
    }
}
