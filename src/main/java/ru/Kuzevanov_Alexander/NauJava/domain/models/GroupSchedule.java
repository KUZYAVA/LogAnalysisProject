package ru.Kuzevanov_Alexander.NauJava.domain.models;

import ru.Kuzevanov_Alexander.NauJava.data.models.Group;
import ru.Kuzevanov_Alexander.NauJava.data.models.ScheduleEvent;

import java.util.List;

public record GroupSchedule(Group group, List<ScheduleEvent> events) {
}
