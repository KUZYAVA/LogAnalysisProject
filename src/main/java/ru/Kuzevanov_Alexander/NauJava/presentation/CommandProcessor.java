package ru.Kuzevanov_Alexander.NauJava.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.Kuzevanov_Alexander.NauJava.data.model.Log;
import ru.Kuzevanov_Alexander.NauJava.domain.LogService;

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
                logService.createLog(Long.valueOf(cmd[1]), cmd[2], cmd[3]);
                System.out.println("Лог успешно добавлен");
            }
            case "findById" -> {
                Log log = logService.findById(Long.valueOf(cmd[1]));
                System.out.println("Найден лог: " + log);
            }
            case "deleteById" -> {
                logService.deleteById(Long.valueOf(cmd[1]));
                System.out.println("Лог успешно удалён");
            }
            case "updateMessage" -> {
                logService.updateMessage(Long.valueOf(cmd[1]), cmd[2]);
                System.out.println("Лог успешно изменён");
            }
            default -> System.out.println("Введена неизвестная команда...");
        }
    }
}
