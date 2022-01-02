package com.phase2.webAPI.repositories;

import com.phase2.webAPI.entity.Locations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationsRepository extends JpaRepository<Locations, Integer> {
    List<Locations> findAllByUser(String user);
    List<Locations> findAllByUserAndLocation(String user, String location);
    boolean existsByUserAndLocation(String user , String location);
}
