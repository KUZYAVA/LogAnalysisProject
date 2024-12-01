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

@SpringBootTest
public class UserServiceTest {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceTest(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Test
    void registerWithSuccess() throws UserExistsException, GroupNotFoundException {
        String username = UUID.randomUUID().toString();
        RegistrationForm form = new RegistrationForm(username, "test password", "МЕН-140207");
        userService.register(form);
        User user = userRepository.findByUsername(username).orElse(null);
        Assertions.assertNotNull(user);
    }

    @Test
    void registerExistingUser() {
        String username = UUID.randomUUID().toString();
        RegistrationForm form = new RegistrationForm(username, "test password", "МЕН-140207");
        assertThrows(UserExistsException.class, () -> {
            userService.register(form);
            userService.register(form);
        });
    }

    @Test
    void registerWithInvalidGroupTitle() {
        String username = UUID.randomUUID().toString();
        RegistrationForm form = new RegistrationForm(username, "test password", "МЕН-XXXXXX");
        assertThrows(GroupNotFoundException.class, () -> userService.register(form));
    }
}
