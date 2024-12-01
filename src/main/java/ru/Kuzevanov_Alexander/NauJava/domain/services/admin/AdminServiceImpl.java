package ru.Kuzevanov_Alexander.NauJava.domain.services.admin;

import org.springframework.stereotype.Service;
import ru.Kuzevanov_Alexander.NauJava.data.models.Group;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.GroupRepository;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;

@Service
public class AdminServiceImpl implements AdminService {

    private final GroupRepository groupRepository;

    public AdminServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void changeVisibility(String groupTitle) throws GroupNotFoundException {
        Group group = groupRepository.findByTitle(groupTitle).orElseThrow(GroupNotFoundException::new);
        group.setHiddenByAdmin(!group.getHiddenByAdmin());
        groupRepository.save(group);
    }
}
