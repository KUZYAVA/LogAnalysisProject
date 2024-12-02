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

/**
 * Service implementation for managing groups. This service interacts with an external API and a
 * database repository to handle group data.
 */
@Service
public class GroupServiceImpl implements GroupService {

    private final ExternalApi externalApi;
    private final GroupRepository groupRepository;

    /**
     * Constructs a new GroupServiceImpl instance.
     *
     * @param externalApi     The external API client for fetching group data.
     * @param groupRepository The repository for accessing and persisting group data.
     */
    public GroupServiceImpl(ExternalApi externalApi, GroupRepository groupRepository) {
        this.externalApi = externalApi;
        this.groupRepository = groupRepository;
    }

    /**
     * {@inheritDoc}
     * <p>This implementation fetches groups from the external API, compares them to existing
     * groups in the database, and saves only the new groups to avoid duplicates.</p>
     */
    @Override
    public void refresh() throws ExternalApiException {
        List<GroupResponse> groupResponses = externalApi.getGroups();
        List<Group> groups = groupResponses.stream().map(ModelMapper::convertToEntity).toList();
        List<Group> allGroups = (List<Group>) groupRepository.findAll();
        Set<Integer> allGroupIds = allGroups.stream().map(Group::getId).collect(Collectors.toSet());
        List<Group> newGroups = groups.stream().filter(group -> !allGroupIds.contains(group.getId())).toList();
        groupRepository.saveAll(newGroups);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> findAllIds() {
        List<Group> groups = (List<Group>) groupRepository.findAll();
        return groups.stream().map(Group::getId).toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Group findById(Integer id) throws GroupNotFoundException {
        return groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Group findByTitle(String title) throws GroupNotFoundException {
        return groupRepository.findByTitle(title).orElseThrow(GroupNotFoundException::new);
    }

    /**
     * {@inheritDoc}
     * <p>This implementation toggles the `hiddenByAdmin` flag of the group.</p>
     */
    @Override
    public void changeVisibility(String title) throws GroupNotFoundException {
        Group group = findByTitle(title);
        group.setHiddenByAdmin(!group.getHiddenByAdmin());
        groupRepository.save(group);
    }
}
