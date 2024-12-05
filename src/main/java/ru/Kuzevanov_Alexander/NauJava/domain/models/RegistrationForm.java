package ru.Kuzevanov_Alexander.NauJava.domain.models;

import org.springframework.lang.Nullable;

/**
 * Represents a form for user registration. This model encapsulates the data submitted during the
 * registration process, including username, password, desired group title, and an indicator of
 * whether the user should be registered as an administrator.
 */
public record RegistrationForm(
        String username,
        String password,
        String groupTitle,
        @Nullable Boolean isAdmin
) {
}
