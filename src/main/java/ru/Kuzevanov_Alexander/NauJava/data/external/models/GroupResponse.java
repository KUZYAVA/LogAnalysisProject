package ru.Kuzevanov_Alexander.NauJava.data.external.models;

/**
 * Represents a response containing group information.
 *
 * @param id   The unique identifier of the group.
 * @param title The title or name of the group.
 */
public record GroupResponse(Integer id, String title) {
}