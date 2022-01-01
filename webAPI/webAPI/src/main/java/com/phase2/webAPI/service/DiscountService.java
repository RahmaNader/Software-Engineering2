package com.phase2.webAPI.service;

import com.phase2.webAPI.entity.Discounts;
import com.phase2.webAPI.repositories.DiscountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DiscountService {

    @Autowired
    private DiscountsRepository discountsRepository;


    public Double getDiscoundByDate(Date date) {
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

    public Double getAllDiscounts(Date date, String area) {
        return (this.getDiscountByArea(area) + this.getDiscoundByDate(date));
    }

    public String addDiscountByDate(Discounts discounts) {
        discountsRepository.save(discounts);
        return "discount by date added";
    }

    public String addDiscountByArea(Discounts discounts) {
        discountsRepository.save(discounts);
        return "discount by time added";
    }
}
