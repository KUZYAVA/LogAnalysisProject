package ru.Kuzevanov_Alexander.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Kuzevanov_Alexander.NauJava.data.models.Group;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.GroupRepository;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.services.group.GroupService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class GroupServiceTest {

    private final GroupService groupService;
    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceTest(GroupService groupService, GroupRepository groupRepository) {
        this.groupService = groupService;
        this.groupRepository = groupRepository;
    }

    @Test
    void refresh() throws ExternalApiException {
        groupRepository.deleteAll();
        groupService.refresh();
        List<Integer> groupIds = groupService.findAllIds();
        Assertions.assertFalse(groupIds.isEmpty());
    }

    @Test
    void findByTitle() throws GroupNotFoundException {
        List<Group> groups = (List<Group>) groupRepository.findAll();
        String title = groups.getFirst().getTitle();
        Group group = groupService.findByTitle(title);
        Assertions.assertNotNull(group);
    }

    @Test
    void findByInvalidTitle() {
        assertThrows(GroupNotFoundException.class, () -> groupService.findByTitle("МЕН-XXXXXX"));
    }

    @Test
    void changeVisibility() throws GroupNotFoundException {
        String groupTitle = "МЕН-140207";
        Group oldGroup = groupService.findByTitle(groupTitle);
        groupService.changeVisibility(groupTitle);
        Group updatedGroup = groupService.findByTitle(groupTitle);
        Assertions.assertNotSame(oldGroup.getHiddenByAdmin(), updatedGroup.getHiddenByAdmin());
    }

    @Test
    void changeVisibilityWithInvalidGroupTitle() {
        assertThrows(GroupNotFoundException.class, () -> groupService.changeVisibility("МЕН-XXXXXX"));
    }
}
