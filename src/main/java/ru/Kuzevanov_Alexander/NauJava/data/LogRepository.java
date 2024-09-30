package ru.Kuzevanov_Alexander.NauJava.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.Kuzevanov_Alexander.NauJava.data.model.Log;

@Component
public class LogRepository implements CrudRepository<Log, Long> {

    private final List<Log> logContainer;

    @Autowired
    public LogRepository(List<Log> logContainer) {
        this.logContainer = logContainer;
    }

    @Override
    public void create(Log log) {
        logContainer.add(log);
    }

    @Override
    public Log read(Long id) {
        return logContainer.stream()
                .filter(log -> log.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Log log) {
        Log previousLog = read(log.getId());
        int index = logContainer.indexOf(previousLog);
        logContainer.set(index, log);
    }

    @Override
    public void delete(Long id) {
        logContainer.removeIf(log -> log.getId().equals(id));
    }
}
