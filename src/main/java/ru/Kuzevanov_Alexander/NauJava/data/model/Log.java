package ru.Kuzevanov_Alexander.NauJava.data.model;

public class Log {
    private Long id;

    private String message;

    private String tag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + ", Message: " + this.message + ", Tag: " + this.tag;
    }
}
