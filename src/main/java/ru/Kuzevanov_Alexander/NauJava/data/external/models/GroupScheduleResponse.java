package ru.Kuzevanov_Alexander.NauJava.data.external.models;

/**
 * Represents a response containing a schedule for a group.  This response includes an array of {@link ScheduleEventResponse} objects.
 *
 * @param events An array of {@link ScheduleEventResponse} objects representing the schedule events.  Can be empty if no events are scheduled.
 */
public record GroupScheduleResponse(ScheduleEventResponse[] events) {
}