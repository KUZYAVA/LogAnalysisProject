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

@Component
public class DbUserDetailsService implements UserDetailsService {

    private final UserService userService;

    private static final String ROLE_PREFIX = "ROLE_";

    public DbUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = userService.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), mapRoles(appUser));
    }

    private Collection<GrantedAuthority> mapRoles(User appUser) {
        return appUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
                .collect(Collectors.toList());
    }
}
