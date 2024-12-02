package ru.Kuzevanov_Alexander.NauJava.data.external.models;

/**
 * Represents a single event in a schedule.
 *
 * @param id                The unique identifier of the schedule event.
 * @param title             The title or name of the event.
 * @param date              The date of the event (e.g., YYYY-MM-DD).
 * @param timeBegin         The start time of the event (e.g., HH:mm).
 * @param timeEnd           The end time of the event (e.g., HH:mm).
 * @param auditoryTitle     The title or name of the room or auditorium where the event takes place.
 * @param auditoryLocation The location of the room or auditorium.
 * @param teacherName       The name of the teacher or instructor.
 */
public record ScheduleEventResponse(
        String id,
        String title,
        String date,
        String timeBegin,
        String timeEnd,
        String auditoryTitle,
        String auditoryLocation,
        String teacherName
) {
}