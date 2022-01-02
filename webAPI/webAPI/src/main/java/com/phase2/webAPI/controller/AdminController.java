package com.phase2.webAPI.controller;

import com.phase2.webAPI.entity.Driver;
import com.phase2.webAPI.entity.Event;
import com.phase2.webAPI.entity.User;
import com.phase2.webAPI.repositories.UserRepository;
import com.phase2.webAPI.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    EventService eventService;

    @Autowired
    DiscountService discountService;

    @GetMapping(value = "/admin/listAllDriverRequests")
    public List<Driver> listAllDriverRequests() {
        return adminService.listAllDriverRequests();
    }

    @GetMapping(value = "/readDrivers")
    public List<Driver> getDrivers() {
        return adminService.allDrivers();
    }

    @GetMapping(value = "/readUsers")
    public List<User> getUsers() {
        return adminService.allUsers();
    }

    @PutMapping(value = "/admin/suspendDriver")
    public String suspendDriver(@RequestBody int id) {
        return adminService.suspendDriver(id);
    }

    @PutMapping(value = "/admin/suspendUser")
    public String suspendUser(@RequestBody int id) {
        return adminService.suspendUser(id);
    }

    @PutMapping(value = "/admin/activateDriver")
    public String activateDriver(@RequestBody int id) {
        return adminService.activateDriver(id);
    }

    @PutMapping(value = "/admin/activateUser")
    public String activateUser(@RequestBody int id) {
        return adminService.activateUser(id);
    }

    @GetMapping(value = "/admin/events")
    public List<Event> displayEvent (){
        return eventService.displayEvent();
    }

    @PostMapping(value = "/admin/addAreaDiscount")
    public String addAreaDiscount(@RequestBody String area){
        return discountService.addDiscountByArea(area);
    }

    @PostMapping(value = "/admin/addDateDiscount")
    public String addDateDiscount(@RequestBody String date){
        return discountService.addDiscountByDate(date);
    }

}
