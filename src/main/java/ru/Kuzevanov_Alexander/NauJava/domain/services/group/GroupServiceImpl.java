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

/**
 * Service layer for managing groups. This service interacts with an external API and a database repository to handle group data.
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
     * Fetches all groups from the external API, converts them to entities, and saves to database.
     *
     * @throws ExternalApiException If there is an error communicating with the external API.
     */
    @Override
    public void load() throws ExternalApiException {
        List<GroupResponse> groupResponses = externalApi.getGroups();
        List<Group> groups = groupResponses.stream().map(ModelMapper::convertToEntity).toList();
        groupRepository.saveAll(groups);
        System.out.printf("%d groups successfully saved%n", groups.size());
    }

    /**
     * Retrieves a list of all group IDs from the database.
     *
     * @return A list of Integer representing the IDs of all groups.
     */
    @Override
    public List<Integer> findAllIds() {
        List<Group> groups = (List<Group>) groupRepository.findAll();
        return groups.stream().map(Group::getId).toList();
    }

    /**
     * Retrieves a group from the database by its title.
     *
     * @param title The title of the group to retrieve.
     * @return The Group object with the matching title.
     * @throws GroupNotFoundException If no group with the given title is found.
     */
    @Override
    public Group findByTitle(String title) throws GroupNotFoundException {
        return groupRepository.findByTitle(title).orElseThrow(GroupNotFoundException::new);
    }

    /**
     * Changes the visibility of a group by toggling its "hiddenByAdmin" flag.
     *
     * @param title The title of the group to change visibility for.
     * @throws GroupNotFoundException If no group with the given title is found.
     */
    @Override
    public void changeVisibility(String title) throws GroupNotFoundException {
        Group group = findByTitle(title);
        group.setHiddenByAdmin(!group.getHiddenByAdmin());
        groupRepository.save(group);
    }
}
