package ru.Kuzevanov_Alexander.NauJava.controller.handlers;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.TeacherNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.TimeNotFoundException;
import ru.Kuzevanov_Alexander.NauJava.dto.ErrorDto;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(java.lang.Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto exception(java.lang.Exception e) {
        if (e instanceof TeacherNotFoundException) {
            return ErrorDto.create("Schedule with such teacher not found");
        } else if (e instanceof TimeNotFoundException) {
            return ErrorDto.create("Schedule with such start or end time not found");
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
