package ru.Kuzevanov_Alexander.NauJava.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.UserExistsException;
import ru.Kuzevanov_Alexander.NauJava.domain.models.RegistrationForm;
import ru.Kuzevanov_Alexander.NauJava.domain.services.user.UserService;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration_page";
    }

    @PostMapping("/registration")
    public String register(RegistrationForm form, Model model) {
        try {
            userService.register(form);
            return "redirect:/login";
        } catch (UserExistsException e) {
            model.addAttribute("message", "Пользователь с таким именем уже зарегистрирован");
            return "registration_page";
        } catch (GroupNotFoundException e) {
            model.addAttribute("message", "Группа не найдена. Пользователь не зарегистрирован");
            return "registration_page";
        }
    }
}
