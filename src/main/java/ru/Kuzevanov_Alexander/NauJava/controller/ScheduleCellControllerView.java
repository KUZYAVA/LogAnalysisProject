package ru.Kuzevanov_Alexander.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.Kuzevanov_Alexander.NauJava.data.model.ScheduleCell;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.schedulecell.ScheduleCellRepository;

@Controller
@RequestMapping("/schedule_cells/view")
public class ScheduleCellControllerView {

    @Autowired
    private ScheduleCellRepository scheduleCellRepository;

    @GetMapping("/list")
    public String scheduleCellsView(Model model) {
        Iterable<ScheduleCell> scheduleCells = scheduleCellRepository.findAll();
        model.addAttribute("scheduleCells", scheduleCells);
        return "schedule_cells";
    }
}
