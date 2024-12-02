package ru.Kuzevanov_Alexander.NauJava.domain.services.schedule_event;

import org.springframework.stereotype.Service;
import ru.Kuzevanov_Alexander.NauJava.data.external.ModelMapper;
import ru.Kuzevanov_Alexander.NauJava.data.models.ScheduleEvent;
import ru.Kuzevanov_Alexander.NauJava.data.external.models.ScheduleEventResponse;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.ScheduleEventRepository;
import ru.Kuzevanov_Alexander.NauJava.data.external.ExternalApi;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;

import java.util.List;

@Service
public class ScheduleEventServiceImpl implements ScheduleEventService {

    private final ExternalApi externalApi;
    private final ScheduleEventRepository scheduleEventRepository;

    public ScheduleEventServiceImpl(ExternalApi externalApi, ScheduleEventRepository scheduleEventRepository) {
        this.externalApi = externalApi;
        this.scheduleEventRepository = scheduleEventRepository;
    }

    @Override
    public void refresh(List<Integer> groupIds) throws ExternalApiException {
        List<ScheduleEventResponse> scheduleEventResponses = externalApi.getScheduleEvents(groupIds);
        List<ScheduleEvent> scheduleEvents = scheduleEventResponses.stream().map(ModelMapper::convertToEntity).toList();
        scheduleEventRepository.saveAll(scheduleEvents);
        System.out.printf("%s schedule events successfully saved", scheduleEvents.size());
    }

    @Override
    public List<ScheduleEvent> findByGroupId(Integer groupId) {
        return scheduleEventRepository.findByGroupId(groupId);
    }
}
