package ru.Kuzevanov_Alexander.NauJava.controller.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.Kuzevanov_Alexander.NauJava.data.model.ScheduleCell;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.schedulecell.ScheduleCellRepositoryCustom;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/custom/schedule_cells")
public class ScheduleCellCustomController {

    @Autowired
    private ScheduleCellRepositoryCustom scheduleCellRepository;

    @GetMapping("/findByStartTimeOrEndTime")
    public List<ScheduleCell> findByStartTimeOrEndTime(@RequestParam String startTime, @RequestParam String endTime) {
        return scheduleCellRepository.findByStartTimeOrEndTime(Timestamp.valueOf(startTime), Timestamp.valueOf(endTime));
    }

    @GetMapping("/findByTeacher")
    public List<ScheduleCell> findByTeacher(@RequestParam String teacherFullName) {
        return scheduleCellRepository.findByTeacher(teacherFullName);
    }
}
