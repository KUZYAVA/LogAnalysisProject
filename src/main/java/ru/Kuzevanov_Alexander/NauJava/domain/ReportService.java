package ru.Kuzevanov_Alexander.NauJava.domain;

import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ReportNotFoundException;

public interface ReportService {

    Long createReport();

    void generateReport(Long reportId);

    String getReportContent(Long reportId) throws ReportNotFoundException;
}
