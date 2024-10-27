package ru.Kuzevanov_Alexander.NauJava.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.Kuzevanov_Alexander.NauJava.domain.ReportService;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ReportNotFoundException;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/generate")
    public Long generateReport() {
        Long id = reportService.createReport();
        reportService.generateReport(id);
        return id;
    }

    @GetMapping("/findById")
    public String getReport(@RequestParam Long id) throws ReportNotFoundException {
        return reportService.getReportContent(id);
    }
}
