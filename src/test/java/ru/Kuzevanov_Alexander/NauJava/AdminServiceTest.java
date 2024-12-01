package ru.Kuzevanov_Alexander.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Kuzevanov_Alexander.NauJava.data.models.Group;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.GroupRepository;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.services.admin.AdminService;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AdminServiceTest {

    private final AdminService adminService;
    private final GroupRepository groupRepository;

    @Autowired
    public AdminServiceTest(AdminService adminService, GroupRepository groupRepository) {
        this.adminService = adminService;
        this.groupRepository = groupRepository;
    }

    @Test
    void changeVisibility() throws GroupNotFoundException {
        String groupTitle = "МЕН-140207";
        Group oldGroup = groupRepository.findByTitle(groupTitle).orElseThrow();
        adminService.changeVisibility(groupTitle);
        Group updatedGroup = groupRepository.findByTitle(groupTitle).orElseThrow();
        Assertions.assertNotSame(oldGroup.getHiddenByAdmin(), updatedGroup.getHiddenByAdmin());
    }

    @Test
    void changeVisibilityWithInvalidGroupTitle() {
        assertThrows(GroupNotFoundException.class, () -> adminService.changeVisibility("МЕН-XXXXXX"));
    }
}
