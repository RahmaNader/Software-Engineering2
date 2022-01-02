package com.phase2.webAPI.service;

import com.phase2.webAPI.entity.Account;
import com.phase2.webAPI.entity.Driver;
import com.phase2.webAPI.entity.User;
import com.phase2.webAPI.repositories.AccountRepository;
import com.phase2.webAPI.repositories.DriverRepository;
import com.phase2.webAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    DriverRepository driverRepository;

        private List<User> users = new ArrayList<>();
        private List<Driver> drivers = new ArrayList<>();

    public AccountService() {
    }

    public String logIn(Account account){
        //            Account loggedIn = new Account(username,0);
//            accountRepository.save(loggedIn);
        accountRepository.save(account);
        return (account.getName()+" logged in");
    }


    public  int loginUser(String username, String password){
        if (userRepository.existsByUserNameAndPassword(username,password)){
            User user = userRepository.findAllByUserName(username);
            if (user.getStatus()) {
                users.add(user);
                user.setToken(users.size());
                userRepository.save(user);
                return users.size();
            }
        }
        return -1;
    }

    public  int loginDriver(String username, String password) {
        if (driverRepository.existsByUserNameAndPassword(username, password)) {
            Driver driver = driverRepository.findAllByUserName(username);
            if(driver.getStatus() == 1) {
                drivers.add(driver);
                driver.setToken(drivers.size());
                driverRepository.save(driver);
                return drivers.size();
            }else{
                return -1;
            }
        }
        return -1;
    }

    public String logOutUser(int token){
        if(users.get(token-1) != null){
            User user = users.remove(token-1);
            user.setToken(-1);
            userRepository.save(user);
            return (user.getUserName() + "logged out");
        }
        return "error";
    }

    public String logOutDriver(int token){
        if(drivers.get(token-1) != null){
            Driver driver = drivers.remove(token-1);
            driver.setToken(-1);
            driverRepository.save(driver);
            return (driver.getUserName() + "logged out");
        }
        return "error";
    }

    //admin login

}
