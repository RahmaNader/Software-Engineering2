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
        private int usersCount =1;
        private int driversCount =1;

    public AccountService() {
    }

    public String logIn(Account account){
        /*Account loggedIn = new Account(username,0);
        accountRepository.save(loggedIn);*/
        accountRepository.save(account);
        return (account.getName()+" logged in");
    }


    public  int loginUser(String username, String password){
        if (userRepository.existsByUserNameAndPassword(username,password)){
            User user = userRepository.findAllByUserName(username);
            if (user.getStatus()) {
                boolean flag = false;
                for (int i=0; i <users.size(); i++) {
                    if (users.get(i).getUserName().equals(user.getUserName())) {
                        flag = true;
                        return users.get(i).getToken();
                    }
                }
                if (!flag){
                    users.add(user);
                    if (users.size() == 1){
                        List<User> temp = userRepository.findAll();
                        for (int i =0; i<temp.size(); i++){
                            temp.get(i).setToken(-1);
                        }
                        userRepository.saveAll(temp);
                    }
                    user.setToken(users.size());
                    userRepository.save(user);
                    return users.size();
                }else
                    return -1;
            }
        }
        return -1;
    }

    public  int loginDriver(String username, String password) {
        if (driverRepository.existsByUserNameAndPassword(username, password)) {
            Driver driver = driverRepository.findAllByUserName(username);
            if(driver.getStatus() == 1) {
                boolean flag = false;
                for (int i=0; i <drivers.size(); i++) {
                    if (drivers.get(i).getUserName().equals(driver.getUserName())) {
                        flag = true;
                        return drivers.get(i).getToken();
                    }
                }
                if (!flag){
                drivers.add(driver);
                if (drivers.size() == 1){
                    List<Driver> temp = driverRepository.findAll();
                    for (int i =0; i<temp.size(); i++){
                        temp.get(i).setToken(-1);
                    }
                    driverRepository.saveAll(temp);
                }
                driver.setToken(drivers.size());
                driverRepository.save(driver);
                return drivers.size();
                }else
                    return -1;
            }
        }
        return -1;
    }

    public String logOutUser(int token){
        if(users.get(token-usersCount) != null){
            User user = users.remove(token-usersCount);
            user.setToken(-1);
            userRepository.save(user);
            usersCount++;
            return (user.getUserName() + " logged out");
        }
        return "error";
    }

    public String logOutDriver(int token){
        if(drivers.get(token-driversCount) != null){
            Driver driver = drivers.remove(token-driversCount);
            driver.setToken(-1);
            driverRepository.save(driver);
            driversCount++;
            return (driver.getUserName() + " logged out");
        }
        return "error";
    }

    //admin login

}
