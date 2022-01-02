package com.phase2.webAPI.service;

import com.phase2.webAPI.entity.Discounts;
import com.phase2.webAPI.entity.Ride;
import com.phase2.webAPI.entity.User;
import com.phase2.webAPI.repositories.DiscountsRepository;
import com.phase2.webAPI.repositories.RideRepository;
import com.phase2.webAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DiscountService {

    @Autowired
    private DiscountsRepository discountsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RideRepository rideRepository;

    public Double checkDiscounts(Ride ride, int token){
        ArrayList<Double> discounts = new ArrayList<>();
        User user;
        if(userRepository.existsByToken(token)) {
            user = userRepository.findAllByToken(token);
            discounts.add(checkFirstRide(user.getUserName()));
            discounts.add(checkAreaDiscount(ride.getDestination()));
            discounts.add(checkPassengers(ride));
            discounts.add(checkPublicHolidays(java.time.LocalDate.now()));
            discounts.add(checkBirthday(user));
        }
        Double result =0.0;
        for (int i = 0; i< discounts.size(); i++){
           result += discounts.get(i);
        }
        return result;
    }

    public Double checkFirstRide(String user){
        if (!rideRepository.existsByUser(user)){
            return 0.1;
        }
        return 0.0;
    }

    public double checkAreaDiscount(String area){
        if(discountsRepository.existsByArea(area)){
            return 0.1;
        }
        return 0.0;
    }

    public double checkPassengers(Ride ride){
        if (ride.getPassengersNum() > 1){
            return 0.05;
        }
        return 0.0;
    }

    public double checkPublicHolidays(LocalDate date){
        if(discountsRepository.existsByTime(date)){
            return 0.05;
        }
        return 0.0;
    }

    public double checkBirthday(User user){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(user.getBirthDate(),dtf);
        Period period = Period.between(date, java.time.LocalDate.now());
        if (period.getDays() == 0 && period.getMonths() ==0){
            return 0.1;
        }
        return 0.0;
    }

    public Double getDiscoundByDate(LocalDate date) {
        double discount = 0;
        List<Discounts> found = discountsRepository.findAllByTime(date);
        for (Discounts discounts : found) {
            discount += discounts.getDiscounts();
        }
        return discount / found.size();
    }

    public Double getDiscountByArea(String area) {
        double discount = 0;
        List<Discounts> found = discountsRepository.findAllByArea(area);
        for (Discounts discounts : found) {
            discount += discounts.getDiscounts();
        }
        return discount / found.size();
    }

    public String addDiscountByDate(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date,dtf);
        if(!discountsRepository.existsByTime(localDate)) {
            Discounts discounts = new Discounts();
            discounts.setDiscounts(0.05);
            discounts.setTime(localDate);
            discountsRepository.save(discounts);
            return "discount by date added";
        }
        return "Already added before";
    }

    public String addDiscountByArea(String area) {
        if(!discountsRepository.existsByArea(area)) {
            Discounts discounts = new Discounts();
            discounts.setDiscounts(0.1);
            discounts.setArea(area);
            discountsRepository.save(discounts);
            return "discount by area added";
        }
        return "Already added before";
    }
}
