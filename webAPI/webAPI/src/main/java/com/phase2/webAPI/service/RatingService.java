package com.phase2.webAPI.service;

import com.phase2.webAPI.entity.Driver;
import com.phase2.webAPI.entity.Rating;
import com.phase2.webAPI.entity.User;
import com.phase2.webAPI.repositories.DriverRepository;
import com.phase2.webAPI.repositories.RatingRepository;
import com.phase2.webAPI.repositories.RideRepository;
import com.phase2.webAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private DriverRepository driverRepository;

    public String rateDriver(Rating rating, int token) {
        User user;
        if(userRepository.existsByToken(token)) {
            user = userRepository.findAllByToken(token);
            rating.setUsername(user.getUserName());
            if(rideRepository.existsByUserAndDriver(rating.getUsername(),rating.getDrivername())) {
                Driver driver = driverRepository.findAllByUserName(rating.getDrivername());
                if (rating.getRate() < 1){
                    rating.setRate(1);
                }else if(rating.getRate() > 5){
                    rating.setRate(5);
                }
                ratingRepository.save(rating);
                driver.setAvgRating(this.calculateAvg(rating.getDrivername()));
                driverRepository.save(driver);
                return "rating created";
            }
        }
        return "wrong input";
    }

    public List<Rating> viewAllRating(int token) {
        Driver driver;
        if(driverRepository.existsByToken(token)) {
            driver = driverRepository.findAllByToken(token);
            return ratingRepository.findAllByDrivername(driver.getUserName());
        }
        return null;
    }

    public double viewAvgRating(int token, String drivername) {
        if(userRepository.existsByToken(token)&&driverRepository.existsByUserName(drivername)) {
            return this.calculateAvg(drivername);
        }
        return -1;
    }

    public double calculateAvg(String drivername){
        List<Rating> found = ratingRepository.findAllByDrivername(drivername);
        double avg = 0;
        int count = 0;
        for (Rating rating : found) {
            avg += rating.getRate();
            count++;
        }
        avg /= count;
        return avg;
    }

}
