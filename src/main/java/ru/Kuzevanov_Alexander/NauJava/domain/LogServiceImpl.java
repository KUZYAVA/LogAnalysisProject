package ru.Kuzevanov_Alexander.NauJava.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Kuzevanov_Alexander.NauJava.data.model.Log;
import ru.Kuzevanov_Alexander.NauJava.data.LogRepository;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void createLog(Long id, String message, String tag) {
        Log newLog = new Log();
        newLog.setId(id);
        newLog.setMessage(message);
        newLog.setTag(tag);
        logRepository.create(newLog);
    }

    @Override
    public Log findById(Long id) {
        return logRepository.read(id);
    }

    @Override
    public void deleteById(Long id) {
        logRepository.delete(id);
    }

    @Override
    public void updateMessage(Long id, String newMessage) {
        Log log = new Log();
        log.setId(id);
        log.setMessage(newMessage);
        logRepository.update(log);
    }
}
