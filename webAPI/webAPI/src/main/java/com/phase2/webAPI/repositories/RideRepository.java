package com.phase2.webAPI.repositories;

import com.phase2.webAPI.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Integer> {
    List<Ride> findAllByUser(String userName);
    Ride findAllById(int id);
    List<Ride> findAllBySource(String source);
    boolean existsByUserAndDriver(String user, String driver);
    boolean existsByUser(String user);
}
