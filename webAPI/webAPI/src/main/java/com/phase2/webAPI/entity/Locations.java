package com.phase2.webAPI.entity;

import javax.persistence.*;

@Entity
public class Locations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String location;

    public String user;

    public Locations() {
    }

    public Locations(String location, String user) {
        this.location = location;
        this.user = user;
    }

    @Column
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Column
    public int getId() {
        return id;
    }
}
