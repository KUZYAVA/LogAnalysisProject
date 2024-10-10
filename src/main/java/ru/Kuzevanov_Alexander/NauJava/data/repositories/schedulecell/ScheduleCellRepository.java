package ru.Kuzevanov_Alexander.NauJava.data.repositories.schedulecell;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.Kuzevanov_Alexander.NauJava.data.model.ScheduleCell;

import java.sql.Timestamp;
import java.util.List;

public interface ScheduleCellRepository extends CrudRepository<ScheduleCell, Long> {

    List<ScheduleCell> findByStartTimeOrEndTime(Timestamp startTime, Timestamp endTime);

    @Query("FROM ScheduleCell WHERE teacher.fullName = :teacherFullName")
    List<ScheduleCell> findByTeacher(String teacherFullName);

    @Modifying
    @Query("delete FROM ScheduleCell WHERE teacher.id = :teacherId")
    void deleteByTeacher(Long teacherId);
}
