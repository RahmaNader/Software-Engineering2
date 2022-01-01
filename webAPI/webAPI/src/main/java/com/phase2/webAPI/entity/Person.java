package com.phase2.webAPI.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {
    private String name;
    private String userName;
    private String mobileNum;
    private String email;
    private String password;

    public Person() {
    }

    public Person(String name, String userName, String mobileNum, String email, String password) {
        this.name = name;
        this.userName = userName;
        this.mobileNum = mobileNum;
        this.email = email;
        this.password = password;
    }

    //setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", mobileNum='" + mobileNum + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
