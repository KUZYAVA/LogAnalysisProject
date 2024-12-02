package ru.Kuzevanov_Alexander.NauJava.domain.services.schedule_event;

import ru.Kuzevanov_Alexander.NauJava.data.models.ScheduleEvent;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;

import java.util.List;

public interface ScheduleEventService {

    void refresh(List<Integer> groupIds) throws ExternalApiException;

    List<ScheduleEvent> findByGroupId(Integer groupId);
}
