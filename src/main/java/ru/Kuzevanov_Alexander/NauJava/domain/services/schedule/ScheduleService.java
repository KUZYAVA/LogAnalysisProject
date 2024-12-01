package ru.Kuzevanov_Alexander.NauJava.domain.services.schedule;

import ru.Kuzevanov_Alexander.NauJava.domain.models.GroupSchedule;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;

public interface ScheduleService {

    void refresh() throws ExternalApiException;

    GroupSchedule getScheduleForCurrentUser() throws GroupNotFoundException;
}
