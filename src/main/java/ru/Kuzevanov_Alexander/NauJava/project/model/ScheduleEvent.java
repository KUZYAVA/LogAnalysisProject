package ru.Kuzevanov_Alexander.NauJava.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "schedule_events")
public class ScheduleEvent {

    @Id
    private String id;

    @Column
    private Integer groupId;

    @Column
    private String title;

    @Column
    private String date;

    @Column
    private String timeBegin;

    @Column
    private String timeEnd;

    @Column
    private String auditoryTitle;

    @Column
    private String auditoryLocation;

    @Column
    private String teacherName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getAuditoryTitle() {
        return auditoryTitle;
    }

    public void setAuditoryTitle(String auditoryTitle) {
        this.auditoryTitle = auditoryTitle;
    }

    public String getAuditoryLocation() {
        return auditoryLocation;
    }

    public void setAuditoryLocation(String auditoryLocation) {
        this.auditoryLocation = auditoryLocation;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
