package ru.Kuzevanov_Alexander.NauJava.controller.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.Kuzevanov_Alexander.NauJava.data.model.ScheduleCell;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.schedulecell.ScheduleCellRepositoryCustom;

import java.sql.Timestamp;

@Controller
@RequestMapping("custom/schedule_cells/view")
public class ScheduleCellCustomControllerView {

    @Autowired
    private ScheduleCellRepositoryCustom scheduleCellRepository;

    @GetMapping("/listByStartTimeOrEndTime")
    public String listByStartTimeOrEndTimeView(Model model, @RequestParam String startTime, @RequestParam String endTime) {
        Iterable<ScheduleCell> scheduleCells = scheduleCellRepository.findByStartTimeOrEndTime(
                Timestamp.valueOf(startTime), Timestamp.valueOf(endTime)
        );
        model.addAttribute("scheduleCells", scheduleCells);
        return "schedule_cells";
    }

    @GetMapping("/listByTeacher")
    public String listByTeacherView(Model model, @RequestParam String teacherFullName) {
        Iterable<ScheduleCell> scheduleCells = scheduleCellRepository.findByTeacher(teacherFullName);
        model.addAttribute("scheduleCells", scheduleCells);
        return "schedule_cells";
    }
}
