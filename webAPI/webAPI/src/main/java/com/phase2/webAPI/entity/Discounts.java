package com.phase2.webAPI.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Discounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    double discounts;
    String area;
    LocalDate time;

    public Discounts() {
    }

    public Discounts(double discounts, String area) {
        this.discounts = discounts;
        this.area = area;
    }

    public Discounts(double discounts, LocalDate time) {
        this.discounts = discounts;
        this.time = time;
    }

    @Column
    public double getDiscounts() {
        return discounts;
    }

    public void setDiscounts(double discounts) {
        this.discounts = discounts;
    }

    @Column
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Column
    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }
}

