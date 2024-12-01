package ru.Kuzevanov_Alexander.NauJava.data.external;

import ru.Kuzevanov_Alexander.NauJava.data.external.models.GroupResponse;
import ru.Kuzevanov_Alexander.NauJava.data.external.models.ScheduleEventResponse;
import ru.Kuzevanov_Alexander.NauJava.data.models.Group;
import ru.Kuzevanov_Alexander.NauJava.data.models.ScheduleEvent;

public class ModelMapper {

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

    public static Group convertToEntity(GroupResponse response) {
        Group group = new Group();
        group.setId(response.id());
        group.setTitle(response.title());
        group.setHiddenByAdmin(false);
        return group;
    }
}
