package ru.Kuzevanov_Alexander.NauJava.data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Status status;

    @Column(columnDefinition="TEXT")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public enum Status {
        CREATED,
        COMPLETED,
        ERROR
    }
}
