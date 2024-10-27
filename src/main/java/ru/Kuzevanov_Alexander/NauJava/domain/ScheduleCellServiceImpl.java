package ru.Kuzevanov_Alexander.NauJava.domain;

import org.springframework.stereotype.Service;
import ru.Kuzevanov_Alexander.NauJava.data.model.ScheduleCell;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.schedulecell.ScheduleCellRepository;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.schedulecell.ScheduleCellRepositoryCustom;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.TeacherNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.TimeNotFoundException;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ScheduleCellServiceImpl implements ScheduleCellService {

    private final ScheduleCellRepositoryCustom scheduleCellRepositoryCustom;
    private final ScheduleCellRepository scheduleCellRepository;

    public ScheduleCellServiceImpl(
            ScheduleCellRepositoryCustom scheduleCellRepositoryCustom,
            ScheduleCellRepository scheduleCellRepository
    ) {
        this.scheduleCellRepositoryCustom = scheduleCellRepositoryCustom;
        this.scheduleCellRepository = scheduleCellRepository;
    }

    @Override
    public List<ScheduleCell> findByStartTimeOrEndTime(String startTime, String endTime) throws TimeNotFoundException {
        List<ScheduleCell> scheduleCells = scheduleCellRepositoryCustom.findByStartTimeOrEndTime(Timestamp.valueOf(startTime), Timestamp.valueOf(endTime));
        if (scheduleCells.isEmpty()) {
            throw new TimeNotFoundException();
        } else {
            return scheduleCells;
        }
    }

    @Override
    public List<ScheduleCell> findByTeacher(String teacherFullName) throws TeacherNotFoundException {
        List<ScheduleCell> scheduleCells = scheduleCellRepositoryCustom.findByTeacher(teacherFullName);
        if (scheduleCells.isEmpty()) {
            throw new TeacherNotFoundException();
        } else {
            return scheduleCells;
        }
    }

    @Override
    public Iterable<ScheduleCell> findAll() {
        return scheduleCellRepository.findAll();
    }
}
