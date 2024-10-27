package ru.Kuzevanov_Alexander.NauJava.domain;

import ru.Kuzevanov_Alexander.NauJava.data.model.Log;
import ru.Kuzevanov_Alexander.NauJava.domain.exception.CreateLogException;
import ru.Kuzevanov_Alexander.NauJava.domain.exception.DeleteLogException;
import ru.Kuzevanov_Alexander.NauJava.domain.exception.UpdateLogException;

import java.util.Optional;

public interface LogService {
    void createLog(String message, String tag) throws CreateLogException;

    Optional<Log> findById(Long id);

    void deleteById(Long id) throws DeleteLogException;

    void updateMessage(Long id, String newMessage) throws UpdateLogException;
}
