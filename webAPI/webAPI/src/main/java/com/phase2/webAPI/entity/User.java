package com.phase2.webAPI.entity;

import javax.persistence.*;

@Entity
public class User extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean status;

    private int token;

    private float balance;


    public User() {
        token = -1;
    }

    public User(String name, String userName, String mobile, String email, String password) {
        super(name, userName, mobile, email, password);
        this.status = true;
        token = -1;
    }

    @Column
    public int getId() {
        return id;
    }

    @Column
    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Column
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Column
    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }
}
