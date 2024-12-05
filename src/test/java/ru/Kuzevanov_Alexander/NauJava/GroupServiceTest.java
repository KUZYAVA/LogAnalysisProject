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

/**
 * Test class for the GroupService. This class contains unit tests to verify the functionality
 * of the GroupService methods, including refresh, find by title, and change visibility.
 */
@SpringBootTest
public class GroupServiceTest {

    private final GroupService groupService;
    private final GroupRepository groupRepository;

    /**
     * Constructs a new GroupServiceTest instance.
     *
     * @param groupService    The GroupService to test.
     * @param groupRepository The GroupRepository to use in tests.
     */
    @Autowired
    public GroupServiceTest(GroupService groupService, GroupRepository groupRepository) {
        this.groupService = groupService;
        this.groupRepository = groupRepository;
    }

    /**
     * Tests the load method of the GroupService.  This test verifies that the load method
     * populates the database with group data and that the findAllIds method returns a non-empty list
     * after the load.
     *
     * @throws ExternalApiException If an error occurs during the refresh operation.
     */
    @Test
    void load() throws ExternalApiException {
        groupService.load();
        List<Integer> groupIds = groupService.findAllIds();
        Assertions.assertFalse(groupIds.isEmpty(), "Expected groupIds to not be empty after refresh");
    }

    /**
     * Tests the findByTitle method of the GroupService with a valid group title.  This test verifies
     * that the method correctly retrieves a Group object when provided with a valid title.
     *
     * @throws GroupNotFoundException If a group with the specified title is not found.
     */
    @Test
    void findByTitle() throws GroupNotFoundException {
        List<Group> groups = (List<Group>) groupRepository.findAll();
        String title = groups.getFirst().getTitle();
        Group group = groupService.findByTitle(title);
        Assertions.assertNotNull(group, "Expected to find a group with the given title");
    }

    /**
     * Tests the findByTitle method of the GroupService with an invalid group title. This test
     * verifies that the method throws a GroupNotFoundException when provided with a non-existent title.
     */
    @Test
    void findByInvalidTitle() {
        assertThrows(GroupNotFoundException.class, () -> groupService.findByTitle("МЕН-XXXXXX"), "Expected GroupNotFoundException for invalid title");
    }

    /**
     * Tests the changeVisibility method of the GroupService. This test verifies that the method
     * correctly toggles the hiddenByAdmin flag of a group.
     *
     * @throws GroupNotFoundException If a group with the specified title is not found.
     */
    @Test
    void changeVisibility() throws GroupNotFoundException {
        String groupTitle = "МЕН-140207";
        Group oldGroup = groupService.findByTitle(groupTitle);
        groupService.changeVisibility(groupTitle);
        Group updatedGroup = groupService.findByTitle(groupTitle);
        Assertions.assertNotSame(oldGroup.getHiddenByAdmin(), updatedGroup.getHiddenByAdmin(), "Expected hiddenByAdmin to be toggled");
    }

    /**
     * Tests the changeVisibility method of the GroupService with an invalid group title. This test
     * verifies that the method throws a GroupNotFoundException when provided with a non-existent title.
     */
    @Test
    void changeVisibilityWithInvalidGroupTitle() {
        assertThrows(GroupNotFoundException.class, () -> groupService.changeVisibility("МЕН-XXXXXX"), "Expected GroupNotFoundException for invalid title");
    }
}
