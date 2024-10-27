package ru.Kuzevanov_Alexander.NauJava.controller.custom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.Kuzevanov_Alexander.NauJava.data.model.ScheduleCell;
import ru.Kuzevanov_Alexander.NauJava.domain.ScheduleCellService;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.TeacherNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.TimeNotFoundException;

@Controller
@RequestMapping("custom/schedule_cells/view")
public class ScheduleCellCustomControllerView {

    private final ScheduleCellService scheduleCellService;

    public ScheduleCellCustomControllerView(ScheduleCellService scheduleCellService) {
        this.scheduleCellService = scheduleCellService;
    }

    @GetMapping("/listByStartTimeOrEndTime")
    public String listByStartTimeOrEndTimeView(Model model, @RequestParam String startTime, @RequestParam String endTime) throws TimeNotFoundException {
        Iterable<ScheduleCell> scheduleCells = scheduleCellService.findByStartTimeOrEndTime(startTime, endTime);
        model.addAttribute("scheduleCells", scheduleCells);
        return "schedule_cells";
    }

    @GetMapping("/listByTeacher")
    public String listByTeacherView(Model model, @RequestParam String teacherFullName) throws TeacherNotFoundException {
        Iterable<ScheduleCell> scheduleCells = scheduleCellService.findByTeacher(teacherFullName);
        model.addAttribute("scheduleCells", scheduleCells);
        return "schedule_cells";
    }
}
