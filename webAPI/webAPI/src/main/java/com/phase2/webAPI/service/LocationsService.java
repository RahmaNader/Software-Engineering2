package com.phase2.webAPI.service;

import com.phase2.webAPI.entity.Driver;
import com.phase2.webAPI.entity.Locations;
import com.phase2.webAPI.entity.Ride;
import com.phase2.webAPI.repositories.DriverRepository;
import com.phase2.webAPI.repositories.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationsService {

    @Autowired
    private LocationsRepository locationsRepository;

    @Autowired
    private DriverRepository driverRepository;

    public String addFavourite(String location, int token) {
        Driver driver;
        if(driverRepository.existsByToken(token)) {
            driver = driverRepository.findAllByToken(token);
            Locations locations = new Locations(location, driver.getUserName());
            locationsRepository.save(locations);
            return "Location created";
        }
        return "wrong token";
    }

    public List<Locations> displayFavourite(int token) {
        Driver driver;
        if(driverRepository.existsByToken(token)) {
            driver = driverRepository.findAllByToken(token);
            return locationsRepository.findAllByUser(driver.getUserName());
        }
        return null;
    }
}
