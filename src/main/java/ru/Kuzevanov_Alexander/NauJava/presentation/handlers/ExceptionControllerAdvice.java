package ru.Kuzevanov_Alexander.NauJava.presentation.handlers;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.UserExistsException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(java.lang.Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto exception(java.lang.Exception e) {
        if (e instanceof ExternalApiException) {
            return ErrorDto.create("Не удалось получить данные из внешнего сервиса");
        } else if (e instanceof GroupNotFoundException) {
            return ErrorDto.create("Группа не найдена");
        } else if (e instanceof UserExistsException) {
            return ErrorDto.create("Пользователь с таким именем уже зарегистрирован");
        }
        return ErrorDto.create(e);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto exception(ResourceNotFoundException e) {
        return ErrorDto.create(e);
    }
}
