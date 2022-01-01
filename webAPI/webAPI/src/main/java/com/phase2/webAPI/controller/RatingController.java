package com.phase2.webAPI.controller;

import com.phase2.webAPI.entity.Rating;
import com.phase2.webAPI.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RatingController {

    @Autowired
    RatingService ratingService;

    @PostMapping(value = "/rateDriver")
    public String rateDriver(@RequestBody Rating rating, @RequestParam int token){
        return ratingService.rateDriver(rating,token);
    }

    @PostMapping(value = "/viewAllRating")
    public List<Rating> viewAllRating(@RequestParam int token){
        return ratingService.viewAllRating(token);
    }

    @PostMapping(value = "/viewAvgRating")
    public double viewAvgRating(@RequestParam int token, @RequestBody String name){
        return ratingService.viewAvgRating(token,name);
    }
}
