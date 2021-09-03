package com.controller;

import com.entity.Advertisement;
import com.entity.Category;
import com.entity.User;
import com.service.AdvertisementService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)

@Controller
@RestController
@RequestMapping("ads/")
public class AdvertisementController {

    final AdvertisementService ADS_SERVICE;

    public AdvertisementController(@Autowired AdvertisementService ads_service) {
        ADS_SERVICE = ads_service;
    }

    //localhost:9999/fakeOlx/ads/save
    @PutMapping("save")
    public void save(@RequestBody Advertisement advertisement) {
        ADS_SERVICE.save(advertisement);
    }

    @PutMapping("update")
    public void update(@RequestBody Advertisement advertisement) {
        ADS_SERVICE.update(advertisement);
    }

    @GetMapping("find/{id}")
    @ResponseBody
    public Advertisement findById(@PathVariable int id) {
        return ADS_SERVICE.findById(id);
    }

    @PutMapping("delete/{id}")
    public void deleteById(@PathVariable("id") int id) {

        ADS_SERVICE.deleteById(id);

    }

    @GetMapping("count-all")
    public int countAll() {

        return ADS_SERVICE.countAll();

    }

    @GetMapping("select-all")
    public List<Advertisement> selectAll() {

        return ADS_SERVICE.selectAll();

    }

    @GetMapping("count-by-category")
    public int countBy(@RequestBody Category category) {

        return ADS_SERVICE.countBy(category);

    }

    @GetMapping("get-by-price")
    @ResponseBody
    public List<Advertisement> getByPriceUp(@RequestBody Category category, @RequestBody int startRow, @RequestBody int amount) {
        return ADS_SERVICE.selectByPriceUp(category, startRow, amount);
    }


}
