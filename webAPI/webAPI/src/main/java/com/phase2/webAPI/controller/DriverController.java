package com.phase2.webAPI.controller;

import com.phase2.webAPI.entity.Driver;
import com.phase2.webAPI.entity.Locations;
import com.phase2.webAPI.entity.Ride;
import com.phase2.webAPI.service.DriverService;
import com.phase2.webAPI.service.AccountService;
import com.phase2.webAPI.service.LocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class DriverController {

    @Autowired
    private DriverService driverService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private LocationsService locationsService;

    @PostMapping(value = "/createDriver")
    public String registerDriver(@RequestBody Driver driver) {
        return driverService.createDriver(driver);
    }

    @PostMapping(value = "/loginDriver")
    public int login(@RequestBody String [] arr){
        return accountService.loginDriver(arr[0],arr[1]);
    }

    @PostMapping (value = "/addFavourite")
    public String addFavourite(@RequestBody String location, @RequestParam int token){
        return locationsService.addFavourite(location, token);
    }

    @PostMapping (value = "/displayFavourite")
    public List<Locations> displayFavourite(@RequestParam int token){
        return locationsService.displayFavourite(token);
    }
    @PostMapping(value = "/displayRides")
    public Set<Ride> displayRides(@RequestParam int token){
        return driverService.displayRides(token);
    }
}
