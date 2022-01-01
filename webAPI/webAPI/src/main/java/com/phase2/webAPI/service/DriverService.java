package com.phase2.webAPI.service;

import com.phase2.webAPI.entity.Account;
import com.phase2.webAPI.entity.Driver;
import com.phase2.webAPI.repositories.AccountRepository;
import com.phase2.webAPI.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public String createDriver(Driver driver) {
        if (driver.getName() == null || driver.getUserName() == null || driver.getPassword() == null || driver.getMobileNum() == null || driver.getNationalID() == null || driver.getDriverLicense() == null)
            return "Missing data: please insert username, name, password, mobile number, national id, and driver licence";
        else if (!driverRepository.existsByUserName(driver.getUserName())) {
            driverRepository.save(driver);
            return "Driver created";
        }
        return "username already exists";
    }

    public List<Driver> allDrivers() {
        return (List<Driver>) driverRepository.findAll();
    }

    public void location(String name, String location){
        Driver driver = driverRepository.findAllByUserName(name);
        driver.setLocation(location);
        driverRepository.save(driver);
    }
}
