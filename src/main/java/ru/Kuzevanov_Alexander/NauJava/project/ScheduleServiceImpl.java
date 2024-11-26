package ru.Kuzevanov_Alexander.NauJava.project;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.Kuzevanov_Alexander.NauJava.project.exceptions.ReadingPageException;
import ru.Kuzevanov_Alexander.NauJava.project.model.ScheduleEvent;
import ru.Kuzevanov_Alexander.NauJava.project.model.ScheduleEventRemote;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleApi scheduleApi;
    private final ScheduleEventRepository scheduleEventRepository;

    public ScheduleServiceImpl(ScheduleApi scheduleApi, ScheduleEventRepository scheduleEventRepository) {
        this.scheduleApi = scheduleApi;
        this.scheduleEventRepository = scheduleEventRepository;
    }

    @Override
    public void refresh() {
        try {
            List<ScheduleEventRemote> scheduleEventsRemote = scheduleApi.getScheduleEvents();
            List<ScheduleEvent> scheduleEvents = scheduleEventsRemote.stream().map(ModelMapper::convertToEntity).collect(Collectors.toList());
            replaceAll(scheduleEvents);
            System.out.printf("%s schedule events successfully saved", scheduleEvents.size());
        } catch (ReadingPageException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ScheduleEvent> findByGroupId(Integer groupId) {
        return scheduleEventRepository.findByGroupId(groupId);
    }

    @Transactional
    private void replaceAll(List<ScheduleEvent> scheduleEvents) {
        scheduleEventRepository.deleteAll();
        scheduleEventRepository.saveAll(scheduleEvents);
    }
}
