package com.phase2.webAPI.controller;

import com.phase2.webAPI.entity.User;
import com.phase2.webAPI.service.AccountService;
import com.phase2.webAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @GetMapping(value = {"", "/"})
    public String home() {
        return "home";
    }

    @PostMapping(value = "/createUser")
    public String registerUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping(value = "/readUsers")
    public List<User> getUsers() {
        return userService.allUsers();
    }

    @PostMapping(value = "/loginUser")
    public int login(@RequestBody String [] arr){
         return accountService.loginUser(arr[0],arr[1]);
    }
}
