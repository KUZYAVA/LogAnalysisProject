package ru.Kuzevanov_Alexander.NauJava.project;

import ru.Kuzevanov_Alexander.NauJava.project.model.ScheduleEvent;
import ru.Kuzevanov_Alexander.NauJava.project.model.ScheduleEventRemote;

public class ModelMapper {

    static ScheduleEvent convertToEntity(ScheduleEventRemote remote) {
        ScheduleEvent entity = new ScheduleEvent();
        entity.setId(remote.getId());

        String groupIdStr = remote.getId().split("-")[1];
        Integer groupId = Integer.parseInt(groupIdStr);
        entity.setGroupId(groupId);

        entity.setTitle(remote.getTitle());
        entity.setDate(remote.getDate());
        entity.setTimeBegin(remote.getTimeBegin());
        entity.setTimeEnd(remote.getTimeEnd());
        entity.setAuditoryTitle(remote.getAuditoryTitle());
        entity.setAuditoryLocation(remote.getAuditoryLocation());
        entity.setTeacherName(remote.getTeacherName());
        return entity;
    }
}
