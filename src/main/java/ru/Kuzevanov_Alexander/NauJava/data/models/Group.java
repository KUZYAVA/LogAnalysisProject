package ru.Kuzevanov_Alexander.NauJava.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a group entity in the database.  This class is annotated for use with JPA (Jakarta Persistence API).
 */
@Entity
@Table(name = "groups")
public class Group {

    /**
     * The unique identifier for the group.
     */
    @Id
    private Integer id;

    /**
     * The title or name of the group.
     */
    @Column
    private String title;

    /**
     * Indicates whether the group is hidden by an administrator.
     */
    @Column
    private Boolean isHiddenByAdmin;

    /**
     * Returns the unique identifier of the group.
     * @return The group's ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the group.
     * @param id The group's ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the title or name of the group.
     * @return The group's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title or name of the group.
     * @param title The group's title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns whether the group is hidden by an administrator.
     * @return True if the group is hidden, false otherwise.
     */
    public Boolean getHiddenByAdmin() {
        return isHiddenByAdmin;
    }

    /**
     * Sets whether the group is hidden by an administrator.
     * @param hiddenByAdmin True to hide the group, false otherwise.
     */
    public void setHiddenByAdmin(Boolean hiddenByAdmin) {
        isHiddenByAdmin = hiddenByAdmin;
    }
}
