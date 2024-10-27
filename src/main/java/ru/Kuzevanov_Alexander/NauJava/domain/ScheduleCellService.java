package ru.Kuzevanov_Alexander.NauJava.domain;

import ru.Kuzevanov_Alexander.NauJava.data.model.ScheduleCell;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.TeacherNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.TimeNotFoundException;

import java.util.List;

public interface ScheduleCellService {

    List<ScheduleCell> findByStartTimeOrEndTime(String startTime, String endTime) throws TimeNotFoundException;

    List<ScheduleCell> findByTeacher(String teacherFullName) throws TeacherNotFoundException;

    Iterable<ScheduleCell> findAll();
}
