package com.phase2.webAPI.entity;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    // 0 = user, 1 = driver
    private int status;


    public Account() {

    }

    public Account(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getName() {
        return name;
    }

    @Column
    public int getId() {
        return id;
    }

    @Column
    public int getStatus() {
        return status;
    }
}
