package ru.Kuzevanov_Alexander.NauJava.domain.services.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.Kuzevanov_Alexander.NauJava.Constants;
import ru.Kuzevanov_Alexander.NauJava.data.models.User;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.UserRepository;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.GroupRepository;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.UserExistsException;
import ru.Kuzevanov_Alexander.NauJava.domain.models.RegistrationForm;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GroupRepository groupRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.groupRepository = groupRepository;
    }

    @Override
    public void register(RegistrationForm form) throws UserExistsException, GroupNotFoundException {
        User userFromDb = userRepository.findByUsername(form.username()).orElse(null);
        if (userFromDb != null) {
            throw new UserExistsException();
        }
        String encodedPassword = passwordEncoder.encode(form.password());
        Integer groupId = groupRepository.findByTitle(form.groupTitle()).orElseThrow(GroupNotFoundException::new).getId();
        User user = new User();
        user.setUsername(form.username());
        user.setPassword(encodedPassword);
        user.setRoles(List.of(Constants.ROLE_USER));
        user.setGroupId(groupId);
        userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(springUser.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
