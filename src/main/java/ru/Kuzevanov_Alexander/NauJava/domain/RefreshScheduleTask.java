package ru.Kuzevanov_Alexander.NauJava.domain;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;
import ru.Kuzevanov_Alexander.NauJava.domain.services.schedule.ScheduleService;

@Component
public class RefreshScheduleTask {

    private final ScheduleService scheduleService;
    private static final long ONE_DAY_RATE = 24 * 60 * 60 * 1000;

    public RefreshScheduleTask(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Scheduled(fixedRate = ONE_DAY_RATE)
    public void refresh() throws ExternalApiException {
        scheduleService.refresh();
    }
}
