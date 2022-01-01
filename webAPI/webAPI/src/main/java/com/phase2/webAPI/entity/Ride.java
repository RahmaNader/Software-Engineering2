package com.phase2.webAPI.entity;

import javax.persistence.*;

@Entity
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String source;

    private String destination;

    private String user;

    private String driver;

    //A = accepted, P = pending, E = ended
    private char status;

    private double price;

    public Ride() {
        this.status = 'P';
        this.driver = "null";
    }

    public Ride(String source, String destination, String user) {
        this.source = source;
        this.destination = destination;
        this.user = user;
        this.status = 'P';
        this.driver = "null";
    }

    @Column
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Column
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Column
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Column
    public String getDriver() {
        return driver;
    }

    @Column
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Column
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column
    public int getId() {
        return id;
    }
}

