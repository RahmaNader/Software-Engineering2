package com.phase2.webAPI.repositories;

import com.phase2.webAPI.entity.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DiscountsRepository extends JpaRepository<Discounts, Integer> {

    List<Discounts> findAllByTime(Date date);

    List<Discounts> findAllByArea(String area);

}
