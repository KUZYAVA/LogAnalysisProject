package ru.Kuzevanov_Alexander.NauJava.domain.services.group;

import ru.Kuzevanov_Alexander.NauJava.data.models.Group;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;

import java.util.List;

public interface GroupService {

    void refresh() throws ExternalApiException;

    List<Integer> findAllIds();

    Group findById(Integer id) throws GroupNotFoundException;

    Group findByTitle(String title) throws GroupNotFoundException;

    void changeVisibility(String title) throws GroupNotFoundException;
}
