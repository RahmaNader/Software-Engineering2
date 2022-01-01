package com.phase2.webAPI.entity;

import javax.persistence.*;

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int rideId;

    private int price;

    private String driverName;

    public Offer() {
    }

    @Column
    public int getId() {
        return id;
    }

    @Column
    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    @Column
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Column
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "rideId=" + rideId +
                ", price=" + price +
                ", driverName='" + driverName + '\'' +
                '}';
    }
}
