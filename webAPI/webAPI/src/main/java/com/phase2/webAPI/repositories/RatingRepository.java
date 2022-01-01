package com.phase2.webAPI.repositories;

import com.phase2.webAPI.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    public boolean existsByDrivername(String drivername);

    public List<Rating> findAllByDrivername(String drivername);
}
