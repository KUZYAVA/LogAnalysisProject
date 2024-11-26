package ru.Kuzevanov_Alexander.NauJava.project.model;

public class ScheduleEventRemote {
    private String id;
    private String title;
    private String date;
    private String timeBegin;
    private String timeEnd;
    private String auditoryTitle;
    private String auditoryLocation;
    private String teacherName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
