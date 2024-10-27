package ru.Kuzevanov_Alexander.NauJava.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.Kuzevanov_Alexander.NauJava.data.model.Log;
import ru.Kuzevanov_Alexander.NauJava.domain.LogService;
import ru.Kuzevanov_Alexander.NauJava.domain.exception.CreateLogException;
import ru.Kuzevanov_Alexander.NauJava.domain.exception.DeleteLogException;
import ru.Kuzevanov_Alexander.NauJava.domain.exception.UpdateLogException;

@Component
public class CommandProcessor {
    private final LogService logService;

    @Autowired
    public CommandProcessor(LogService logService) {
        this.logService = logService;
    }

    public void processCommand(String input) {
        String[] cmd = input.split(" ");
        switch (cmd[0]) {
            case "create" -> {
                try {
                    logService.createLog(cmd[1], cmd[2]);
                    System.out.println("Лог успешно добавлен");
                } catch (CreateLogException e) {
                    System.out.println("Не удалось добавить лог");
                }
            }
            case "findById" -> {
                Log log = logService.findById(Long.valueOf(cmd[1])).orElse(null);
                if (log != null) {
                    System.out.println("Найден лог: " + log);
                } else {
                    System.out.println("Не удалось найти лог");
                }
            }
            case "deleteById" -> {
                try {
                    logService.deleteById(Long.valueOf(cmd[1]));
                    System.out.println("Лог успешно удалён");
                } catch (DeleteLogException e) {
                    System.out.println("Не удалось удалить лог");
                }
            }
            case "updateMessage" -> {
                try {
                    logService.updateMessage(Long.valueOf(cmd[1]), cmd[2]);
                    System.out.println("Лог успешно изменён");
                } catch (UpdateLogException e) {
                    System.out.println("Не удалось изменить лог");
                }
            }
            default -> System.out.println("Введена неизвестная команда...");
        }
    }
}
