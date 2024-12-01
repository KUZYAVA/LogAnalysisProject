package ru.Kuzevanov_Alexander.NauJava.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    private Integer id;

    @Column
    private String title;

    @Column
    private Boolean isHiddenByAdmin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getHiddenByAdmin() {
        return isHiddenByAdmin;
    }

    public void setHiddenByAdmin(Boolean hiddenByAdmin) {
        isHiddenByAdmin = hiddenByAdmin;
    }
}
