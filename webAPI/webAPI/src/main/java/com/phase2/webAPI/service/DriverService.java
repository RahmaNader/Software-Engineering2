package com.phase2.webAPI.service;

import com.phase2.webAPI.entity.Account;
import com.phase2.webAPI.entity.Driver;
import com.phase2.webAPI.entity.Locations;
import com.phase2.webAPI.entity.Ride;
import com.phase2.webAPI.repositories.AccountRepository;
import com.phase2.webAPI.repositories.DriverRepository;
import com.phase2.webAPI.repositories.LocationsRepository;
import com.phase2.webAPI.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private LocationsRepository locationsRepository;

    @Autowired
    private RideRepository rideRepository;

    public String createDriver(Driver driver) {
        if (driver.getName() == null || driver.getUserName() == null || driver.getPassword() == null || driver.getMobileNum() == null || driver.getNationalID() == null || driver.getDriverLicense() == null)
            return "Missing data: please insert username, name, password, mobile number, national id, and driver licence";
        else if (!driverRepository.existsByUserName(driver.getUserName())) {
            driverRepository.save(driver);
            return "Driver created";
        }
        return "username already exists";
    }


    public void location(String name, String location){
        Driver driver = driverRepository.findAllByUserName(name);
        driver.setLocation(location);
        driverRepository.save(driver);
    }

    public Set<Ride> displayRides(int token){
        Driver driver;
        Set<Ride> rides = new HashSet<>() ;
        if(driverRepository.existsByToken(token)) {
            driver = driverRepository.findAllByToken(token);
            List<Locations> locations = locationsRepository.findAllByUser(driver.getUserName());
            for (Locations location : locations){
                List<Ride> ride = rideRepository.findAllBySource(location.getLocation());
                rides.addAll(ride);
            }
            return rides;
        }
        return null;
    }
}
