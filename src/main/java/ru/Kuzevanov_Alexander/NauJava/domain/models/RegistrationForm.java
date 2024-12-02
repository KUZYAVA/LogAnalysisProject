package ru.Kuzevanov_Alexander.NauJava.domain.models;

public record RegistrationForm(
        String username,
        String password,
        String groupTitle,
        Boolean isHiddenByAdmin
) {
}
