package com.phase2.webAPI.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class User extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean status;

    private int token;

    private float balance;

    private String birthDate;

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
    public boolean getStatus() {
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

    @Column
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
