package ru.Kuzevanov_Alexander.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Kuzevanov_Alexander.NauJava.data.models.User;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.UserRepository;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.UserExistsException;
import ru.Kuzevanov_Alexander.NauJava.domain.models.RegistrationForm;
import ru.Kuzevanov_Alexander.NauJava.domain.services.user.UserService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the {@link UserService}. This class contains unit tests to verify the
 * functionality of the {@link UserService} methods, including successful registration,
 * registration of an existing user, and registration with an invalid group title.
 */
@SpringBootTest
public class UserServiceTest {

    private final UserService userService;
    private final UserRepository userRepository;

    /**
     * Constructs a new UserServiceTest instance.
     *
     * @param userService    The {@link UserService} to test.
     * @param userRepository The {@link UserRepository} to use in tests.
     */
    @Autowired
    public UserServiceTest(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    /**
     * Tests successful user registration. This test verifies that a new user can be successfully
     * registered using a valid registration form.
     *
     * @throws UserExistsException    If a user with the same username already exists.
     * @throws GroupNotFoundException If the specified group does not exist.
     */
    @Test
    void registerWithSuccess() throws UserExistsException, GroupNotFoundException {
        String username = UUID.randomUUID().toString();
        RegistrationForm form = new RegistrationForm(username, "test password", "МЕН-140207", false);
        userService.register(form);
        User user = userRepository.findByUsername(username).orElse(null);
        Assertions.assertNotNull(user, "Expected a user to be created");
    }

    /**
     * Tests registration of an existing user. This test verifies that an attempt to register a
     * user with an existing username throws a {@link UserExistsException}.
     */
    @Test
    void registerExistingUser() {
        String username = UUID.randomUUID().toString();
        RegistrationForm form = new RegistrationForm(username, "test password", "МЕН-140207", false);
        assertThrows(UserExistsException.class, () -> {
            userService.register(form);
            userService.register(form); //Second attempt should throw exception
        }, "Expected UserExistsException for duplicate username");
    }

    /**
     * Tests user registration with an invalid group title. This test verifies that an attempt to
     * register a user with a non-existent group title throws a {@link GroupNotFoundException}.
     */
    @Test
    void registerWithInvalidGroupTitle() {
        String username = UUID.randomUUID().toString();
        RegistrationForm form = new RegistrationForm(username, "test password", "МЕН-XXXXXX", false);
        assertThrows(GroupNotFoundException.class, () -> userService.register(form), "Expected GroupNotFoundException for invalid group title");
    }
}
