package com.phase2.webAPI.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String source;

    private String destination;

    private String user;

    private String driver;

    private LocalDate date;

    private int passengersNum;

    //A = accepted, P = pending, E = ended
    private char status;

    private double price;

    private double priceAfterDiscount = -1;

    private Double discounts;

    public Ride() {
        this.status = 'P';
        this.driver = "null";
        this.passengersNum = 1;
        date = LocalDate.now();
    }

    public Ride(String source, String destination, String user) {
        this.source = source;
        this.destination = destination;
        this.user = user;
        this.status = 'P';
        date = LocalDate.now();
        passengersNum = 1;
        this.driver = "null";
        date = LocalDate.now();
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

    @Column
    public double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(double priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Double discounts) {
        this.discounts = discounts;
    }

    public int getPassengersNum() {
        return passengersNum;
    }

    public void setPassengersNum(int passengersNum) {
        this.passengersNum = passengersNum;
    }


}

