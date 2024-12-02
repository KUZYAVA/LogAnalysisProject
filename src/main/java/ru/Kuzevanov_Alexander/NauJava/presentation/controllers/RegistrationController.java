package ru.Kuzevanov_Alexander.NauJava.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.UserExistsException;
import ru.Kuzevanov_Alexander.NauJava.domain.models.RegistrationForm;
import ru.Kuzevanov_Alexander.NauJava.domain.services.user.UserService;

/**
 * Controller for handling user registration.  This controller manages the registration form and
 * processes user registration requests, handling potential exceptions during the registration process.
 */
@Controller
public class RegistrationController {

    private final UserService userService;

    /**
     * Constructs a new RegistrationController instance.
     * @param userService The service for managing user registration.
     */
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Handles GET requests to the "/registration" URL, returning the registration view.
     * @return The name of the registration view template ("registration_page").
     */
    @GetMapping("/registration")
    public String registration() {
        return "registration_page";
    }

    /**
     * Handles POST requests to the "/registration" URL, processing the user registration.
     * If successful, redirects to the login page. If a UserExistsException or GroupNotFoundException
     * occurs, an appropriate error message is added to the model, and the registration page is redisplayed.
     * @param form The registration form submitted by the user.
     * @param model The model to add attributes to (for error messages).
     * @return The name of the view to render, either "registration_page" with error messages or redirecting to "/login".
     */
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
