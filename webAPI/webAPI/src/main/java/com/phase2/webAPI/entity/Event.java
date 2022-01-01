package com.phase2.webAPI.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private LocalDateTime time;
    private String username;
    private String drivername;
    private EventName eventName = EventName.noEvent;
    private int rideId;
    private double price;

    public Event() {
    }

    public Event(int rideId, String userName, String captainName, double price, LocalDateTime time, EventName eventName) {
        this.price = price;
        this.drivername = captainName;
        this.time = time;
        this.username = userName;
        this.rideId = rideId;
        this.eventName = eventName;
    }

    @Column
    public int getId() {
        return id;
    }

    @Column
    public LocalDateTime getTime() {
        return time;
    }

    @Column
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column
    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    @Column
    public EventName getEventName() {
        return eventName;
    }

    public void setEventName(EventName eventName) {
        this.eventName = eventName;
    }

    @Column
    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    @Column
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

