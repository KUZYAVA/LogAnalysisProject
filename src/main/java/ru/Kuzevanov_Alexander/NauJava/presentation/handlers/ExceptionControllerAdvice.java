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

/**
 * A Spring ControllerAdvice class for handling exceptions globally across the application.  This class intercepts exceptions thrown
 * by controllers and provides standardized error responses.
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * Handles generic exceptions. This acts as a fallback handler for any exceptions not specifically handled by other methods in this class.
     * Provides specific error messages for known custom exceptions (ExternalApiException, GroupNotFoundException, UserExistsException),
     * otherwise returns a generic error message based on the exception's message.
     *
     * @param e The exception that was thrown.
     * @return An ErrorDto containing the error message.
     */
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

    /**
     * Handles ResourceNotFoundException specifically.  This provides a more specific HTTP status code (NOT_FOUND) for resource-related errors.
     *
     * @param e The ResourceNotFoundException that was thrown.
     * @return An ErrorDto containing the error message from the exception.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto exception(ResourceNotFoundException e) {
        return ErrorDto.create(e);
    }
}
