package ru.Kuzevanov_Alexander.NauJava.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.Kuzevanov_Alexander.NauJava.data.models.Group;
import ru.Kuzevanov_Alexander.NauJava.data.models.ScheduleEvent;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.services.group.GroupService;
import ru.Kuzevanov_Alexander.NauJava.domain.services.schedule_event.ScheduleEventService;
import ru.Kuzevanov_Alexander.NauJava.domain.services.user.UserService;

import java.util.List;

@Controller
public class ScheduleController {

    private final UserService userService;
    private final GroupService groupService;
    private final ScheduleEventService scheduleEventService;

    public ScheduleController(UserService userService, GroupService groupService, ScheduleEventService scheduleEventService) {
        this.userService = userService;
        this.groupService = groupService;
        this.scheduleEventService = scheduleEventService;
    }

    @GetMapping("/schedule")
    public String scheduleView(Model model) {
        try {
            Integer groupId = userService.getCurrentUser().getGroupId();
            Group group = groupService.findById(groupId);
            model.addAttribute("groupTitle", group.getTitle());
            if (group.getHiddenByAdmin()) {
                model.addAttribute("message", "Расписание скрыто");
            } else {
                List<ScheduleEvent> scheduleEvents = scheduleEventService.findByGroupId(groupId);
                model.addAttribute("scheduleEvents", scheduleEvents);
            }
        } catch (GroupNotFoundException e) {
            model.addAttribute("message", "Расписания нет, т.к группа не найдена");
        }
        return "schedule_page";
    }
}
