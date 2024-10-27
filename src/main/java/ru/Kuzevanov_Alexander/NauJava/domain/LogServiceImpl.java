package ru.Kuzevanov_Alexander.NauJava.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Kuzevanov_Alexander.NauJava.data.exception.CrudRepositoryException;
import ru.Kuzevanov_Alexander.NauJava.data.model.Log;
import ru.Kuzevanov_Alexander.NauJava.data.LogRepository;
import ru.Kuzevanov_Alexander.NauJava.domain.exception.CreateLogException;
import ru.Kuzevanov_Alexander.NauJava.domain.exception.DeleteLogException;
import ru.Kuzevanov_Alexander.NauJava.domain.exception.UpdateLogException;

import java.util.Optional;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void createLog(String message, String tag) throws CreateLogException {
        try {
            logRepository.create(message, tag);
        } catch (CrudRepositoryException e) {
            throw new CreateLogException();
        }
    }

    @Override
    public Optional<Log> findById(Long id) {
        return logRepository.read(id);
    }

    @Override
    public void deleteById(Long id) throws DeleteLogException {
        try {
            logRepository.delete(id);
        } catch (CrudRepositoryException e) {
            throw new DeleteLogException();
        }
    }

    @Override
    public void updateMessage(Long id, String newMessage) throws UpdateLogException {
        Log log = new Log();
        log.setId(id);
        log.setMessage(newMessage);
        try {
            logRepository.update(log);
        } catch (CrudRepositoryException e) {
            throw new UpdateLogException();
        }
    }
}
