package ru.Kuzevanov_Alexander.NauJava.presentation.handlers;

/**
 * Data Transfer Object (DTO) representing an error.  This class encapsulates an error message for easier handling and transmission.
 */
public class ErrorDto {
    private String message;

    /**
     * Private constructor to prevent direct instantiation. Use static factory methods {@link #create(Throwable)} or {@link #create(String)} instead.
     *
     * @param message The error message.
     */
    private ErrorDto(String message) {
        this.message = message;
    }

    /**
     * Retrieves the error message.
     *
     * @return The error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message.  Generally, it's preferred to use the static factory methods instead of directly modifying the message after creation.
     *
     * @param message The error message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Creates an ErrorDto from a Throwable object.  The error message will be extracted from the Throwable.
     *
     * @param e The Throwable object containing the error information.
     * @return A new ErrorDto instance with the message from the Throwable.
     */
    public static ErrorDto create(Throwable e) {
        return new ErrorDto(e.getMessage());
    }

    /**
     * Creates an ErrorDto from a given string message.
     *
     * @param message The error message.
     * @return A new ErrorDto instance with the provided message.
     */
    public static ErrorDto create(String message) {
        return new ErrorDto(message);
    }
}
