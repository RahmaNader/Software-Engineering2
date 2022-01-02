package com.phase2.webAPI.controller;

import com.phase2.webAPI.entity.Offer;
import com.phase2.webAPI.entity.Ride;
import com.phase2.webAPI.service.DiscountService;
import com.phase2.webAPI.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class RideController {

    @Autowired
    private RideService riderService;

    @Autowired
    private DiscountService discountService;

    @PostMapping(value = "/requestRide")
    public String registerRide(@RequestBody Ride ride, @RequestParam int token) {
        Double discount = discountService.checkDiscounts(ride , token);
        return riderService.requestRide(ride, token , discount);
    }

    @GetMapping(value = "/readRides")
    public List<Ride> getRides() {
        return riderService.allRides();
    }

    @PostMapping(value = "/complete")
    public String complete(@RequestBody int id, @RequestParam int token){
        riderService.completeRide(id,token );
        return "ride completed";
    }

    //user accepting ride
    @PostMapping (value = "/acceptRide")
    public String acceptRide(@RequestBody int id , @RequestParam int token){
        riderService.acceptRequest(id,token );
        return "request accepted";
    }

    @PostMapping(value = "/makeOffer")
    public String makeOffer(@RequestBody Offer offer , @RequestParam int token){
        return riderService.makeOffer(offer, token);
    }

    @PostMapping(value = "/viewOffer")
    public List<Offer> viewOffer(@RequestBody int id, @RequestParam int token){
        return riderService.viewOffer(id, token);
    }

    @PostMapping(value = "/driverArrived")
    public String driverArrived(@RequestBody int id, @RequestParam int token){
        return riderService.driverArrivedToSource(id, token);
    }




}
