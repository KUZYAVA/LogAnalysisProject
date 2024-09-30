package ru.Kuzevanov_Alexander.NauJava.domain;

import ru.Kuzevanov_Alexander.NauJava.data.model.Log;

public interface LogService {
    void createLog(Long id, String message, String tag);

    Log findById(Long id);

    void deleteById(Long id);

    void updateMessage(Long id, String newMessage);
}
