package ru.Kuzevanov_Alexander.NauJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.Kuzevanov_Alexander.NauJava.data.model.ScheduleCell;
import ru.Kuzevanov_Alexander.NauJava.domain.ScheduleCellService;

@Controller
@RequestMapping("/schedule_cells/view")
public class ScheduleCellControllerView {

    private final ScheduleCellService scheduleCellService;

    public ScheduleCellControllerView(ScheduleCellService scheduleCellService) {
        this.scheduleCellService = scheduleCellService;
    }

    @GetMapping("/list")
    public String scheduleCellsView(Model model) {
        Iterable<ScheduleCell> scheduleCells = scheduleCellService.findAll();
        model.addAttribute("scheduleCells", scheduleCells);
        return "schedule_cells";
    }
}
