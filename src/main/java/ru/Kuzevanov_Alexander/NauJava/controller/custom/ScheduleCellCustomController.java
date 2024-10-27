package ru.Kuzevanov_Alexander.NauJava.controller.custom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.Kuzevanov_Alexander.NauJava.data.model.ScheduleCell;
import ru.Kuzevanov_Alexander.NauJava.domain.ScheduleCellService;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.TeacherNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.TimeNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/custom/schedule_cells")
public class ScheduleCellCustomController {

    private final ScheduleCellService scheduleCellService;

    public ScheduleCellCustomController(ScheduleCellService scheduleCellService) {
        this.scheduleCellService = scheduleCellService;
    }

    @GetMapping("/findByStartTimeOrEndTime")
    public List<ScheduleCell> findByStartTimeOrEndTime(@RequestParam String startTime, @RequestParam String endTime) throws TimeNotFoundException {
        return scheduleCellService.findByStartTimeOrEndTime(startTime, endTime);
    }

    @GetMapping("/findByTeacher")
    public List<ScheduleCell> findByTeacher(@RequestParam String teacherFullName) throws TeacherNotFoundException {
        return scheduleCellService.findByTeacher(teacherFullName);
    }
}
