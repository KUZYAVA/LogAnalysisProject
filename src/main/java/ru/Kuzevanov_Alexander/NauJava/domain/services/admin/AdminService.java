package ru.Kuzevanov_Alexander.NauJava.domain.services.admin;

import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;

public interface AdminService {

    void changeVisibility(String groupTitle) throws GroupNotFoundException;
}
