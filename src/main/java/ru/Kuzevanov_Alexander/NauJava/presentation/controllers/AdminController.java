package ru.Kuzevanov_Alexander.NauJava.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.services.admin.AdminService;

@Controller
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String adminView() {
        return "admin_page";
    }

    @PostMapping("/admin")
    public void changeVisibility(String title, Model model) {
        try {
            adminService.changeVisibility(title);
        } catch (GroupNotFoundException e) {
            model.addAttribute("message", "Группа не найдена");
        }
    }
}
