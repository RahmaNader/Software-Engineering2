package com.phase2.webAPI.repositories;

import com.phase2.webAPI.entity.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface DiscountsRepository extends JpaRepository<Discounts, Integer> {

    boolean existsByArea(String area);

    boolean existsByTime(LocalDate date);

    List<Discounts> findAllByTime(LocalDate date);

    List<Discounts> findAllByArea(String area);

}
