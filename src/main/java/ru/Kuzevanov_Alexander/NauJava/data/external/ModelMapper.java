package ru.Kuzevanov_Alexander.NauJava.data.external;

import ru.Kuzevanov_Alexander.NauJava.data.external.models.GroupResponse;
import ru.Kuzevanov_Alexander.NauJava.data.external.models.ScheduleEventResponse;
import ru.Kuzevanov_Alexander.NauJava.data.models.Group;
import ru.Kuzevanov_Alexander.NauJava.data.models.ScheduleEvent;

/**
 * A utility class for mapping data from external API responses to internal entity models.
 */
public class ModelMapper {

    /**
     * Converts a {@link ScheduleEventResponse} from the external API to a {@link ScheduleEvent} entity.
     * Extracts the group ID from the response ID, assuming a specific format.
     *
     * @param response The {@link ScheduleEventResponse} object from the external API.
     * @return The corresponding {@link ScheduleEvent} entity.
     * @throws NumberFormatException If the group ID cannot be parsed from the response ID.
     */
    public static ScheduleEvent convertToEntity(ScheduleEventResponse response) {
        String groupIdStr = response.id().split("-")[1];
        Integer groupId = Integer.parseInt(groupIdStr);
        ScheduleEvent entity = new ScheduleEvent();
        entity.setId(response.id());
        entity.setGroupId(groupId);
        entity.setTitle(response.title());
        entity.setDate(response.date());
        entity.setTimeBegin(response.timeBegin());
        entity.setTimeEnd(response.timeEnd());
        entity.setAuditoryTitle(response.auditoryTitle());
        entity.setAuditoryLocation(response.auditoryLocation());
        entity.setTeacherName(response.teacherName());
        return entity;
    }

    /**
     * Converts a {@link GroupResponse} from the external API to a {@link Group} entity.
     * Sets the `hiddenByAdmin` field to `false` by default.
     *
     * @param response The {@link GroupResponse} object from the external API.
     * @return The corresponding {@link Group} entity.
     */
    public static Group convertToEntity(GroupResponse response) {
        Group group = new Group();
        group.setId(response.id());
        group.setTitle(response.title());
        group.setHiddenByAdmin(false);
        return group;
    }
}
