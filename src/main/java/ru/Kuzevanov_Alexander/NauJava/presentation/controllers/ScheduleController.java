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

/**
 * Controller for displaying the schedule view. This controller retrieves schedule events for the
 * currently logged-in user's group and handles potential exceptions during data retrieval.
 */
@Controller
public class ScheduleController {

    private final UserService userService;
    private final GroupService groupService;
    private final ScheduleEventService scheduleEventService;

    /**
     * Constructs a new ScheduleController instance.
     * @param userService The service for managing user information.
     * @param groupService The service for managing group information.
     * @param scheduleEventService The service for managing schedule event information.
     */
    public ScheduleController(UserService userService, GroupService groupService, ScheduleEventService scheduleEventService) {
        this.userService = userService;
        this.groupService = groupService;
        this.scheduleEventService = scheduleEventService;
    }

    /**
     * Handles GET requests to the "/schedule" URL, displaying the schedule page.
     * This method retrieves the current user's group ID, fetches the group and schedule events,
     * and adds them to the model for display.  Error handling is included for cases where
     * the group is not found.
     * @param model The model to add attributes to (group title, schedule events, and error messages).
     * @return The name of the schedule view template ("schedule_page").
     */
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
