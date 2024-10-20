package ru.Kuzevanov_Alexander.NauJava.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.Kuzevanov_Alexander.NauJava.data.model.User;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = userRepository.findByUsername(username)
                .stream()
                .findFirst()
                .orElse(null);
        if (appUser != null) {
            return new
                    org.springframework.security.core.userdetails.User(
                    appUser.getUsername(), appUser.getPassword(),
                    mapRoles(appUser));
        } else {
            throw new UsernameNotFoundException("user not found");
        }
    }

    private Collection<GrantedAuthority> mapRoles(User appUser) {
        return appUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }
}
