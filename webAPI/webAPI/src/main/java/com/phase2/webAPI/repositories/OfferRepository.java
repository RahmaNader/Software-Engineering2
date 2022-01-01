package com.phase2.webAPI.repositories;

import com.phase2.webAPI.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    List<Offer> findAllByRideId(int id);
    Offer findAllById(int id);
}
