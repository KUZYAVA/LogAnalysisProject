package ru.Kuzevanov_Alexander.NauJava.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.Kuzevanov_Alexander.NauJava.data.models.Group;
import ru.Kuzevanov_Alexander.NauJava.domain.models.GroupSchedule;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.services.schedule.ScheduleService;

@Controller
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedule")
    public String scheduleView(Model model) {
        try {
            GroupSchedule groupSchedule = scheduleService.getScheduleForCurrentUser();
            Group group = groupSchedule.group();
            model.addAttribute("groupTitle", group.getTitle());
            if (group.getHiddenByAdmin()) {
                model.addAttribute("message", "Расписание скрыто");
            } else {
                model.addAttribute("scheduleEvents", groupSchedule.events());
            }
        } catch (GroupNotFoundException e) {
            model.addAttribute("message", "Расписания нет, т.к группа не найдена");
        }
        return "schedule_page";
    }
}
