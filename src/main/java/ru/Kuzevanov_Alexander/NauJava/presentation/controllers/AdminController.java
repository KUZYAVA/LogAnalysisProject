package ru.Kuzevanov_Alexander.NauJava.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.services.group.GroupService;

/**
 * Controller for managing administrative tasks, specifically handling group visibility changes.
 */
@Controller
public class AdminController {

    private final GroupService groupService;

    /**
     * Constructs a new AdminController instance.
     *
     * @param groupService The service for managing groups.
     */
    public AdminController(GroupService groupService) {
        this.groupService = groupService;
    }

    /**
     * Handles GET requests to the "/admin" URL, returning the admin view.
     *
     * @return The name of the admin view template ("admin_page").
     */
    @GetMapping("/admin")
    public String adminView() {
        return "admin_page";
    }

    /**
     * Handles POST requests to the "/admin" URL, changing the visibility of a group.
     * If the group is not found, an error message is added to the model.
     *
     * @param title The title of the group to change visibility.
     * @param model The model to add attributes to (for error messages).
     */
    @PostMapping("/admin")
    public void changeVisibility(String title, Model model) {
        try {
            groupService.changeVisibility(title);
        } catch (GroupNotFoundException e) {
            model.addAttribute("message", "Группа не найдена");
        }
    }
}
