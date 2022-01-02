package com.phase2.webAPI.service;

import com.phase2.webAPI.entity.Driver;
import com.phase2.webAPI.entity.User;
import com.phase2.webAPI.repositories.DiscountsRepository;
import com.phase2.webAPI.repositories.DriverRepository;
import com.phase2.webAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    DriverRepository driverRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DiscountsRepository discountsRepository;

    public List<Driver> listAllDriverRequests() {
        List<Driver> all = driverRepository.findAll();
        List<Driver> ret = new ArrayList<>();
        for (Driver driver : all) {
            // 0 = driver not activated
            if (driver.getStatus()==0) ret.add(driver);
        }
        return ret;
    }
    public List<Driver> allDrivers() {
        return (List<Driver>) driverRepository.findAll();
    }
    public List<User> allUsers() {
        return (List<User>) userRepository.findAll();
    }

    public String suspendDriver(int id) {
        Driver driver = driverRepository.findById(id).get();
        driver.setStatus(0);
        driverRepository.save(driver);
        return ("driver " + driver.getUserName() + " is suspended");
    }

    public String activateDriver(int id) {
        Driver driver = driverRepository.findById(id).get();
        driver.setStatus(1);
        driverRepository.save(driver);
        return ("driver " + driver.getUserName() + " is activated");
    }

    public String suspendUser(int id) {
        User user = userRepository.findById(id).get();
        user.setStatus(false);
        userRepository.save(user);
        return ("user " + user.getUserName() + " is suspended");
    }

    public String activateUser(int id) {
        User user = userRepository.findById(id).get();
        user.setStatus(true);
        userRepository.save(user);
        return ("user " + user.getUserName() + " is activated");
    }
}
