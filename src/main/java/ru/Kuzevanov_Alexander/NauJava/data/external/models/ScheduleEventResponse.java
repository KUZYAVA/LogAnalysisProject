package ru.Kuzevanov_Alexander.NauJava.data.external.models;

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
