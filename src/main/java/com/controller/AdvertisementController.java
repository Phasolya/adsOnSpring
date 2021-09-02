package com.controller;

import com.entity.Advertisement;
import com.service.AdvertisementService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    //    @PutMapping("update{id}")
    @PutMapping("update")
    public void update(@RequestBody Advertisement advertisement) {
        ADS_SERVICE.update(advertisement);
    }

    @GetMapping("find/{id}")
    @ResponseBody
    public Advertisement findById(@PathVariable int id) {
        return ADS_SERVICE.findById(id);
    }
}
