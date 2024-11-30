package ru.Kuzevanov_Alexander.NauJava.project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.Kuzevanov_Alexander.NauJava.project.model.ScheduleEvent;

import java.util.List;

@Controller
public class ScheduleControllerView {

    private final ScheduleService scheduleService;

    public ScheduleControllerView(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedule")
    public String scheduleView(Model model) {
        List<ScheduleEvent> scheduleEvents = scheduleService.findByGroupId(60937);
        model.addAttribute("scheduleEvents", scheduleEvents);
        return "schedule_events";
    }
}
