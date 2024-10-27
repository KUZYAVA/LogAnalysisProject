package ru.Kuzevanov_Alexander.NauJava.data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "classrooms")
public class Classroom {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String roomNumber;

    @Column
    private String floor;

    @Column
    private Boolean isLarge;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Boolean getLarge() {
        return isLarge;
    }

    public void setLarge(Boolean large) {
        isLarge = large;
    }
}
