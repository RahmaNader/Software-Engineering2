package com.phase2.webAPI.service;

import com.phase2.webAPI.entity.Account;
import com.phase2.webAPI.entity.User;
import com.phase2.webAPI.repositories.AccountRepository;
import com.phase2.webAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String createUser(User user) {
        if (user.getName() == null || user.getUserName() == null || user.getPassword() == null || user.getMobileNum() == null)
            return "Missing data: please insert username, name, password, and mobile number";
        else if (!userRepository.existsByUserName(user.getUserName())) {
            user.setStatus(true);
            userRepository.save(user);
            return "User created";
        }
        return "UserName already exists";
    }

    public List<User> allUsers() {
        return (List<User>) userRepository.findAll();
    }

}
