package ru.Kuzevanov_Alexander.NauJava.project;

import ru.Kuzevanov_Alexander.NauJava.project.exceptions.NetworkException;
import ru.Kuzevanov_Alexander.NauJava.project.model.ScheduleEvent;

import java.util.List;

public interface ScheduleService {

    void refresh();

    List<ScheduleEvent> findByGroupId(Integer groupId);

    Integer getGroupId(String groupTitle) throws NetworkException;
}
