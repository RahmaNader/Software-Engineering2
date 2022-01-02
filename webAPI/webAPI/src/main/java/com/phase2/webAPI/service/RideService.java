package com.phase2.webAPI.service;

import com.phase2.webAPI.entity.*;
import com.phase2.webAPI.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationsRepository locationsRepository;


    public String requestRide(Ride ride, int token, Double discount) {
        User user;
        if(userRepository.existsByToken(token)) {
            user = userRepository.findAllByToken(token);
            ride.setStatus('P');
            ride.setUser(user.getUserName());
            ride.setDiscounts(discount);
            rideRepository.save(ride);
            return "ride created";
        }
        return "user not found";
    }

    public List<Ride> viewRequests(Driver driver) {
        List<Ride> all = rideRepository.findAll();
        List<Ride> ret = new ArrayList<>();
        for (Ride r : all) {
            if (r.getDriver().equals(driver.getUserName())) {
                ret.add(r);
            }
        }
        return ret;
    }

    public List<Ride> allRides() {
        return (List<Ride>) rideRepository.findAll();
    }

    public List<Ride> viewRequestsForUser(int token){
        User user;
        if(userRepository.existsByToken(token)) {
            user = userRepository.findAllByToken(token);
            return rideRepository.findAllByUser(user.getUserName());
        }
        return null;
    }

    public void completeRide(int id, int token){
        Driver driver;
        if(driverRepository.existsByToken(token)) {
            driver = driverRepository.findAllByToken(token);
            Ride ride = rideRepository.findAllById(id);
            ride.setStatus('E');
            driver.setInRide(0);
            driver.setLocation(ride.getDestination());
            EventName eventName = EventName.capArrivesDest;
            Event event = new Event(ride.getId(), ride.getUser(), ride.getDriver(), ride.getPrice(), LocalDateTime.now().atZone(ZoneId.systemDefault()).toLocalDateTime(), eventName);
            eventRepository.save(event);
            driverRepository.save(driver);
            rideRepository.save(ride);
        }
    }

    public void acceptRequest(int id, int token) {
        User user;
        if (userRepository.existsByToken(token)) {
            Offer offer = offerRepository.findAllById(id);
            Ride ride = rideRepository.findAllById(offer.getRideId());
            ride.setDriver(offer.getDriverName());
            ride.setPrice(offer.getPrice());
            ride.setStatus('A');
            ride.setStatus('A');// A, P
            double discount= ride.getDiscounts();
            ride.setPriceAfterDiscount((1-discount)*ride.getPrice());
            rideRepository.save(ride);
            Driver driver = driverRepository.findAllByUserName(ride.getDriver());
            driver.setInRide(1);
            driverRepository.save(driver);
            EventName eventName = EventName.UserAccept;
            Event event = new Event(ride.getId(), ride.getUser(), ride.getDriver(), ride.getPrice(), LocalDateTime.now().atZone(ZoneId.systemDefault()).toLocalDateTime(), eventName);
            eventRepository.save(event);
        }
    }

    //make offer //view all offers
    public String makeOffer(Offer offer, int token){
        Driver driver;
        if(driverRepository.existsByToken(token)) {
            driver = driverRepository.findAllByToken(token);
            if (driver.getInRide()==0 && (rideRepository.existsById(offer.getRideId()))) {
                offer.setDriverName(driver.getUserName());
                offerRepository.save(offer);
                String username = rideRepository.findAllById(offer.getRideId()).getUser();
                EventName eventName = EventName.captainOffer;
                Event event = new Event(offer.getRideId(), username, offer.getDriverName(), offer.getPrice(), LocalDateTime.now().atZone(ZoneId.systemDefault()).toLocalDateTime(), eventName);
                eventRepository.save(event);
                return "offer created";
            }
        }
        return "driver or ride id don't exist";
    }

    public List<Offer> viewOffer(int id, int token) {
        User user;
        if (userRepository.existsByToken(token)) {
            return offerRepository.findAllByRideId(id);
        }
        return null;
    }

    public String driverArrivedToSource(int id, int token){
        Driver driver;
        Ride ride = rideRepository.findAllById(id);
        if(driverRepository.existsByToken(token)) {
            driver = driverRepository.findAllByToken(token);
            driver.setLocation(ride.getSource());
            driver.setInRide(1);
            driverRepository.save(driver);
            EventName eventName = EventName.capArrivesLoc;
            Event event = new Event(ride.getId(),ride.getUser(),ride.getDriver(),ride.getPrice(), LocalDateTime.now().atZone(ZoneId.systemDefault()).toLocalDateTime(),eventName);
            eventRepository.save(event);
            return "location changed";
        }
        return "wrong id number or token number";
    }
}
