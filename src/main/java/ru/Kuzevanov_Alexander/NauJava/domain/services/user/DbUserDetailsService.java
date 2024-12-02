package ru.Kuzevanov_Alexander.NauJava.domain.services.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.Kuzevanov_Alexander.NauJava.data.models.User;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * A Spring Security UserDetailsService implementation that retrieves user details from a database.
 * This service loads user information, including username, password, and roles, from a UserService
 * and converts it into a Spring Security UserDetails object for authentication.
 */
@Component
public class DbUserDetailsService implements UserDetailsService {

    private final UserService userService;

    private static final String ROLE_PREFIX = "ROLE_";

    /**
     * Constructs a new DbUserDetailsService instance.
     * @param userService The UserService to retrieve user information from.
     */
    public DbUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    /**
     * {@inheritDoc}
     * <p>This method loads user information from the database based on the given username.  It retrieves the user
     * from the {@link UserService}, and if found, constructs a Spring Security {@link org.springframework.security.core.userdetails.User}
     * object. If the user is not found, it throws a {@link UsernameNotFoundException}.</p>
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = userService.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), mapRoles(appUser));
    }

    /**
     * Maps user roles to Spring Security GrantedAuthorities.
     * @param appUser The user whose roles need to be mapped.
     * @return A collection of GrantedAuthorities representing the user's roles.
     */
    private Collection<GrantedAuthority> mapRoles(User appUser) {
        return appUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
                .collect(Collectors.toList());
    }
}
