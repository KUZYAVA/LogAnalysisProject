package ru.Kuzevanov_Alexander.NauJava.domain;

import org.springframework.stereotype.Service;
import ru.Kuzevanov_Alexander.NauJava.data.model.Report;
import ru.Kuzevanov_Alexander.NauJava.data.model.Teacher;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.ReportRepository;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.TeacherRepository;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.UserRepository;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ReportNotFoundException;

import java.util.concurrent.CompletableFuture;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;

    public ReportServiceImpl(
            ReportRepository reportRepository,
            UserRepository userRepository,
            TeacherRepository teacherRepository
    ) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Long createReport() {
        Report newReport = new Report();
        newReport.setStatus(Report.Status.CREATED);
        Report createdReport = reportRepository.save(newReport);
        return createdReport.getId();
    }

    @Override
    public void generateReport(Long reportId) {
        CompletableFuture.runAsync(() -> {
            ReportContentInfo info = generateReportContent();

            reportRepository.findById(reportId).ifPresent(report -> {
                report.setStatus(info.status);
                if (info.status == Report.Status.COMPLETED) {
                    report.setContent(info.content);
                }
                reportRepository.save(report);
            });
        });
    }

    private ReportContentInfo generateReportContent() {
        try {
            long startTime = System.currentTimeMillis();

            CompletableFuture<UserCountInfo> userCountFuture = CompletableFuture.supplyAsync(this::getUserCountInfo);
            CompletableFuture<TeachersInfo> teachersFuture = CompletableFuture.supplyAsync(this::getTeachersInfo);

            UserCountInfo userCountInfo = userCountFuture.join();
            TeachersInfo teachersInfo = teachersFuture.join();

            StringBuilder html = new StringBuilder("<html><body><h1>Отчет</h1>" + "<h2>Количество зарегистрированных пользователей: " + userCountInfo.userCount + "</h2>" + "<h2>Список учителей: </h2><ul>");
            for (Teacher teacher : teachersInfo.teachers) {
                html.append("<li>").append(teacher.getFullName()).append("</li>");
            }
            html.append("</ul>" + "<h2>Время, затраченное на вычисление количества пользователей: ")
                    .append(userCountInfo.spentTime)
                    .append(" мс</h2>")
                    .append("<h2>Время, затраченное на вычисление списка учителей: ")
                    .append(teachersInfo.spentTime)
                    .append(" мс</h2>")
                    .append("<h2>Общее время, затраченное на формирование отчета: ")
                    .append(System.currentTimeMillis() - startTime)
                    .append(" мс</h2>")
                    .append("</body></html>");

            return new ReportContentInfo(Report.Status.COMPLETED, html.toString());
        } catch (Exception e) {
            return new ReportContentInfo(Report.Status.ERROR, "");
        }
    }

    private UserCountInfo getUserCountInfo() {
        long startTime = System.currentTimeMillis();
        long userCount = userRepository.count();
        long spentTime = System.currentTimeMillis() - startTime;
        return new UserCountInfo(userCount, spentTime);
    }

    private TeachersInfo getTeachersInfo() {
        long startTime = System.currentTimeMillis();
        Iterable<Teacher> teachers = teacherRepository.findAll();
        long spentTime = System.currentTimeMillis() - startTime;
        return new TeachersInfo(teachers, spentTime);
    }

    @Override
    public String getReportContent(Long reportId) throws ReportNotFoundException {
        Report report = reportRepository.findById(reportId).orElseThrow(ReportNotFoundException::new);
        return report.getContent();
    }

    record ReportContentInfo(Report.Status status, String content) {
    }

    record UserCountInfo(long userCount, long spentTime) {
    }

    record TeachersInfo(Iterable<Teacher> teachers, long spentTime) {
    }

}
