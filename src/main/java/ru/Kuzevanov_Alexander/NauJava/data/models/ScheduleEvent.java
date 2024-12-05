package ru.Kuzevanov_Alexander.NauJava.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a schedule event in the database.  Each instance corresponds to a single entry in the `schedule_events` table.
 */
@Entity
@Table(name = "schedule_events")
public class ScheduleEvent {

    /**
     * Unique identifier for the schedule event.
     */
    @Id
    private String id;

    /**
     * ID of the group associated with this event.
     */
    @Column
    private Integer groupId;

    /**
     * Title or name of the event.
     */
    @Column
    private String title;

    /**
     * Date of the event.  Consider using a dedicated Date/LocalDate type for better date handling.
     */
    @Column
    private String date;

    /**
     * Start time of the event. Consider using a dedicated Time/LocalTime type for better time handling.
     */
    @Column
    private String timeBegin;

    /**
     * End time of the event. Consider using a dedicated Time/LocalTime type for better time handling.
     */
    @Column
    private String timeEnd;

    /**
     * Name or title of the auditory (classroom) where the event takes place.
     */
    @Column
    private String auditoryTitle;

    /**
     * Location of the auditory (e.g., building and room number).
     */
    @Column
    private String auditoryLocation;

    /**
     * Name of the teacher conducting the event.
     */
    @Column
    private String teacherName;

    /**
     * Returns the unique identifier of the schedule event.
     *
     * @return The ID of the event.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the schedule event.
     *
     * @param id The ID to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the ID of the group associated with this event.
     *
     * @return The group ID.
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * Sets the ID of the group associated with this event.
     *
     * @param groupId The group ID to set.
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * Returns the title or name of the event.
     *
     * @return The title of the event.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title or name of the event.
     *
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the date of the event.
     *
     * @return The date of the event.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the event.
     *
     * @param date The date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event.
     */
    public String getTimeBegin() {
        return timeBegin;
    }

    /**
     * Sets the start time of the event.
     *
     * @param timeBegin The start time to set.
     */
    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event.
     */
    public String getTimeEnd() {
        return timeEnd;
    }

    /**
     * Sets the end time of the event.
     *
     * @param timeEnd The end time to set.
     */
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    /**
     * Returns the name or title of the auditory.
     *
     * @return The auditory title.
     */
    public String getAuditoryTitle() {
        return auditoryTitle;
    }

    /**
     * Sets the name or title of the auditory.
     *
     * @param auditoryTitle The auditory title to set.
     */
    public void setAuditoryTitle(String auditoryTitle) {
        this.auditoryTitle = auditoryTitle;
    }

    /**
     * Returns the location of the auditory.
     *
     * @return The auditory location.
     */
    public String getAuditoryLocation() {
        return auditoryLocation;
    }

    /**
     * Sets the location of the auditory.
     *
     * @param auditoryLocation The auditory location to set.
     */
    public void setAuditoryLocation(String auditoryLocation) {
        this.auditoryLocation = auditoryLocation;
    }

    /**
     * Returns the name of the teacher.
     *
     * @return The teacher's name.
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * Sets the name of the teacher.
     *
     * @param teacherName The teacher's name to set.
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}