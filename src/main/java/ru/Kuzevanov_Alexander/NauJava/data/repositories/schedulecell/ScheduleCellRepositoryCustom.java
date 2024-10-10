package ru.Kuzevanov_Alexander.NauJava.data.repositories.schedulecell;

import ru.Kuzevanov_Alexander.NauJava.data.model.ScheduleCell;

import java.sql.Timestamp;
import java.util.List;

public interface ScheduleCellRepositoryCustom {

    List<ScheduleCell> findByStartTimeOrEndTime(Timestamp startTime, Timestamp endTime);

    List<ScheduleCell> findByTeacher(String teacherFullName);
}
