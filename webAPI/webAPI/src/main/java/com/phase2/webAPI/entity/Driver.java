package com.phase2.webAPI.entity;

import javax.persistence.*;

@Entity
public class Driver extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String driverLicense;

    private int status;

    private String nationalID;

    private double avgRating;
    //indicate if driver is in ride , 0 available to offer ride, 1 unavailable
    private int inRide;

    private  String location;

    private int token;

    public Driver() {
        inRide = 0;
        status = 0;
        location = "";
        token = -1;
    }

    public Driver(String name,String userName, String mobile, String email, String password, String nationalID, String driverLicense) {
        super(name, userName, mobile, email, password);
        this.nationalID = nationalID;
        this.status = 0;
        this.driverLicense = driverLicense;
        token = -1;
    }

    @Column
    public int getId() {
        return id;
    }

    @Column
    public String getDriverLicense() {
        return driverLicense;
    }


    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    @Column
    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    @Column
    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    @Column
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column
    public int getInRide() {
        return inRide;
    }

    public void setInRide(int inRide) {
        this.inRide = inRide;
    }

    @Column
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column
    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }
}