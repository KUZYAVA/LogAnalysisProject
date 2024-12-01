package ru.Kuzevanov_Alexander.NauJava.domain.services.user;

import ru.Kuzevanov_Alexander.NauJava.data.models.User;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.UserExistsException;
import ru.Kuzevanov_Alexander.NauJava.domain.models.RegistrationForm;

public interface UserService {

    void register(RegistrationForm form) throws UserExistsException, GroupNotFoundException;

    User getCurrentUser();
}
