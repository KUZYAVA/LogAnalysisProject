package ru.Kuzevanov_Alexander.NauJava.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.services.group.GroupService;

@Controller
public class AdminController {

    private final GroupService groupService;

    public AdminController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/admin")
    public String adminView() {
        return "admin_page";
    }

    @PostMapping("/admin")
    public void changeVisibility(String title, Model model) {
        try {
            groupService.changeVisibility(title);
        } catch (GroupNotFoundException e) {
            model.addAttribute("message", "Группа не найдена");
        }
    }
}
