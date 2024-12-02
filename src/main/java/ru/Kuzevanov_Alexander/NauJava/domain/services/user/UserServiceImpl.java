package ru.Kuzevanov_Alexander.NauJava.domain.services.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.Kuzevanov_Alexander.NauJava.Constants;
import ru.Kuzevanov_Alexander.NauJava.data.models.User;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.UserRepository;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.UserExistsException;
import ru.Kuzevanov_Alexander.NauJava.domain.models.RegistrationForm;
import ru.Kuzevanov_Alexander.NauJava.domain.services.group.GroupService;

import java.util.List;

/**
 * Service implementation for managing users. This service handles user registration, retrieval of
 * the currently logged-in user, and user retrieval by username.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GroupService groupService;

    /**
     * Constructs a new UserServiceImpl instance.
     *
     * @param userRepository  The repository for accessing and persisting user data.
     * @param passwordEncoder The password encoder for hashing user passwords.
     * @param groupService    The service for managing groups.
     */
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, GroupService groupService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.groupService = groupService;
    }

    /**
     * {@inheritDoc}
     * <p>This implementation checks for existing users, encodes the password, determines the user role,
     * retrieves the group ID, and then saves the new user to the database.</p>
     */
    @Override
    public void register(RegistrationForm form) throws UserExistsException, GroupNotFoundException {
        User userFromDb = userRepository.findByUsername(form.username()).orElse(null);
        if (userFromDb != null) {
            throw new UserExistsException();
        }
        String encodedPassword = passwordEncoder.encode(form.password());
        String role = form.isAdmin() ? Constants.ROLE_ADMIN : Constants.ROLE_USER;
        Integer groupId = groupService.findByTitle(form.groupTitle()).getId();
        User user = new User();
        user.setUsername(form.username());
        user.setPassword(encodedPassword);
        user.setRoles(List.of(role));
        user.setGroupId(groupId);
        userRepository.save(user);
    }

    /**
     * {@inheritDoc}
     * <p>This implementation retrieves the currently authenticated user from the Spring Security context
     * and then retrieves the full User object from the database using the username.</p>
     */
    @Override
    public User getCurrentUser() throws UsernameNotFoundException {
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findByUsername(springUser.getUsername());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}
