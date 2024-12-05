package ru.Kuzevanov_Alexander.NauJava.domain.services.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.data.models.User;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.UserExistsException;
import ru.Kuzevanov_Alexander.NauJava.domain.models.RegistrationForm;

/**
 * Service interface for managing users. This interface defines methods for user registration,
 * retrieval of the currently logged-in user, and retrieval of users by username.
 */
public interface UserService {

    /**
     * Registers a new user using the provided registration form.
     *
     * @param form The registration form containing user details.
     * @throws UserExistsException    If a user with the same username or email already exists.
     * @throws GroupNotFoundException If the specified group for the user does not exist.
     */
    void register(RegistrationForm form) throws UserExistsException, GroupNotFoundException;

    /**
     * Retrieves the currently authenticated user.
     *
     * @return The currently logged-in User object.
     * @throws UsernameNotFoundException If no user is currently authenticated.
     */
    User getCurrentUser() throws UsernameNotFoundException;

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The User object if found.
     * @throws UsernameNotFoundException If no user with the given username is found.
     */
    User findByUsername(String username) throws UsernameNotFoundException;
}
