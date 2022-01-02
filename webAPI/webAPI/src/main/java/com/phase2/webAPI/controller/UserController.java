package com.phase2.webAPI.controller;

import com.phase2.webAPI.entity.User;
import com.phase2.webAPI.service.AccountService;
import com.phase2.webAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/loginUser")
    public int login(@RequestBody String [] arr){
         return accountService.loginUser(arr[0],arr[1]);
    }
    @PostMapping(value = "/logoutUser")
    public String logout(@RequestParam int token){
        return accountService.logOutUser(token);
    }
}
