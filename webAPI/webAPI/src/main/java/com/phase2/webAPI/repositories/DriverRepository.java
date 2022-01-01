package com.phase2.webAPI.repositories;

import com.phase2.webAPI.entity.Driver;
import com.phase2.webAPI.entity.Ride;
import com.phase2.webAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    public boolean existsByUserName(String userName);
    Driver findAllByUserName(String userName);
    boolean existsByUserNameAndPassword(String userName, String password);
    boolean existsByToken(int token);
    Driver findAllByToken(int token);
}
