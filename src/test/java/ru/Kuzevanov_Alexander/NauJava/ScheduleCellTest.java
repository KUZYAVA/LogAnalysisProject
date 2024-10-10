package ru.Kuzevanov_Alexander.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Kuzevanov_Alexander.NauJava.data.model.ScheduleCell;
import ru.Kuzevanov_Alexander.NauJava.data.model.Teacher;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.schedulecell.ScheduleCellRepository;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.schedulecell.ScheduleCellRepositoryCustom;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.TeacherRepository;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
public class ScheduleCellTest {

    private final ScheduleCellRepository scheduleCellRepository;
    private final ScheduleCellRepositoryCustom scheduleCellRepositoryCustom;
    private final TeacherRepository teacherRepository;

    @Autowired
    public ScheduleCellTest(
            ScheduleCellRepository scheduleCellRepository,
            ScheduleCellRepositoryCustom scheduleCellRepositoryCustom,
            TeacherRepository teacherRepository
    ) {
        this.scheduleCellRepository = scheduleCellRepository;
        this.scheduleCellRepositoryCustom = scheduleCellRepositoryCustom;
        this.teacherRepository = teacherRepository;
    }

    @Test
    void testFindByStartTimeOrEndTime() {
        Timestamp startTime = Timestamp.valueOf("2024-09-23 10:10:10");
        Timestamp startTime2 = Timestamp.valueOf("2024-09-22 10:10:10");
        Timestamp endTime = Timestamp.valueOf("2024-09-27 10:10:10");

        ScheduleCell scheduleCell = new ScheduleCell();
        scheduleCell.setStartTime(startTime);
        scheduleCellRepository.save(scheduleCell);

        List<ScheduleCell> scheduleCells = scheduleCellRepository.findByStartTimeOrEndTime(startTime, endTime);
        Assertions.assertFalse(scheduleCells.isEmpty());

        List<ScheduleCell> scheduleCells2 = scheduleCellRepository.findByStartTimeOrEndTime(startTime2, endTime);
        Assertions.assertTrue(scheduleCells2.isEmpty());
    }

    @Test
    void testFindByTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFullName("teacher");
        teacherRepository.save(teacher);

        ScheduleCell scheduleCell = new ScheduleCell();
        scheduleCell.setTeacher(teacher);
        scheduleCellRepository.save(scheduleCell);

        List<ScheduleCell> scheduleCells = scheduleCellRepository.findByTeacher(teacher.getFullName());
        Assertions.assertFalse(scheduleCells.isEmpty());

        List<ScheduleCell> scheduleCells2 = scheduleCellRepository.findByTeacher(teacher.getFullName() + "random text");
        Assertions.assertTrue(scheduleCells2.isEmpty());
    }

    @Test
    void testFindByStartTimeOrEndTimeCustom() {
        Timestamp startTime = Timestamp.valueOf("2024-09-23 10:10:10");
        Timestamp startTime2 = Timestamp.valueOf("2024-09-22 10:10:10");
        Timestamp endTime = Timestamp.valueOf("2024-09-27 10:10:10");

        ScheduleCell scheduleCell = new ScheduleCell();
        scheduleCell.setStartTime(startTime);
        scheduleCellRepository.save(scheduleCell);

        List<ScheduleCell> scheduleCells = scheduleCellRepositoryCustom.findByStartTimeOrEndTime(startTime, endTime);
        Assertions.assertFalse(scheduleCells.isEmpty());

        List<ScheduleCell> scheduleCells2 = scheduleCellRepositoryCustom.findByStartTimeOrEndTime(startTime2, endTime);
        Assertions.assertTrue(scheduleCells2.isEmpty());
    }

    @Test
    void testFindByTeacherCustom() {
        Teacher teacher = new Teacher();
        teacher.setFullName("teacher");
        teacherRepository.save(teacher);

        ScheduleCell scheduleCell = new ScheduleCell();
        scheduleCell.setTeacher(teacher);
        scheduleCellRepository.save(scheduleCell);

        List<ScheduleCell> scheduleCells = scheduleCellRepositoryCustom.findByTeacher(teacher.getFullName());
        Assertions.assertFalse(scheduleCells.isEmpty());

        List<ScheduleCell> scheduleCells2 = scheduleCellRepositoryCustom.findByTeacher(teacher.getFullName() + "random text");
        Assertions.assertTrue(scheduleCells2.isEmpty());
    }
}
