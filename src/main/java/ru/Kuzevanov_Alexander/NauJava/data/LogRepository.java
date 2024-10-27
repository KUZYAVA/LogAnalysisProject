package ru.Kuzevanov_Alexander.NauJava.data;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.Kuzevanov_Alexander.NauJava.data.exception.CrudRepositoryException;
import ru.Kuzevanov_Alexander.NauJava.data.model.Log;

@Component
public class LogRepository implements CrudRepository<Log, Long> {

    private final List<Log> logContainer;

    @Autowired
    public LogRepository(List<Log> logContainer) {
        this.logContainer = logContainer;
    }

    @Override
    public void create(String message, String tag) throws CrudRepositoryException {
        try {
            long newId;
            if (logContainer.isEmpty()) {
                newId = 1L;
            } else {
                newId = logContainer.getLast().getId() + 1;
            }
            Log newLog = new Log();
            newLog.setId(newId);
            newLog.setMessage(message);
            newLog.setTag(tag);
            logContainer.add(newLog);
        } catch (Exception e) {
            throw new CrudRepositoryException();
        }
    }

    @Override
    public Optional<Log> read(Long id) {
        return logContainer.stream()
                .filter(log -> log.getId().equals(id))
                .map(Log::createCopy)
                .findFirst();
    }

    @Override
    public void update(Log log) throws CrudRepositoryException {
        Log previousLog = read(log.getId()).orElseThrow(CrudRepositoryException::new);
        int index = findIndexById(logContainer, previousLog.getId());
        logContainer.set(index, log);
    }

    @Override
    public void delete(Long id) throws CrudRepositoryException {
        boolean isRemoved = logContainer.removeIf(log -> log.getId().equals(id));
        if (!isRemoved) {
            throw new CrudRepositoryException();
        }
    }

    private static int findIndexById(List<Log> list, long id) {
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return i;
            }
        }
        return index;
    }
}
