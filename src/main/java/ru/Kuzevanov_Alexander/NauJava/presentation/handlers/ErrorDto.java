package ru.Kuzevanov_Alexander.NauJava.presentation.handlers;

public class ErrorDto {
    private String message;

    private ErrorDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ErrorDto create(Throwable e) {
        return new ErrorDto(e.getMessage());
    }

    public static ErrorDto create(String message) {
        return new ErrorDto(message);
    }
}
