package com.controller;

import com.domain.Advertisement;
import com.dto.CategoryDto;
import com.dto.CategoryHeaderDto;
import com.dto.CategoryHeaderPriceDto;
import com.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("ads/")
public class AdvertisementController {

    final AdvertisementService ADS_SERVICE;

    //localhost:9999/ads/save
    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("ad")
    public void save(@Valid @RequestBody Advertisement advertisement) {
        ADS_SERVICE.saveAndSentNotifications(advertisement);
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("ad/{id}")
    public Advertisement findById(@PathVariable int id) {
        return ADS_SERVICE.find(id);
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @PutMapping("ad")
    public void update(@Valid @RequestBody Advertisement advertisement) {
        ADS_SERVICE.updateAndSentNotifications(advertisement);
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("ad/{id}")
    public void deleteById(@PathVariable("id") int id) {
        ADS_SERVICE.delete(id);
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("count")
    public int countAll() {
        return ADS_SERVICE.countAll();
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("ad-page-c")
    public Page<Advertisement> getPageAdsByCategoryDto(@Valid @RequestBody CategoryDto categoryDto) {
        return ADS_SERVICE.getByCategoryDto(categoryDto);
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("ad-page-ch")
    public Page<Advertisement> getPageAdsByCategoryAndHeaderDto(@Valid @RequestBody CategoryHeaderDto categoryHeaderDto) {
        return ADS_SERVICE.getByCategoryHeaderDto(categoryHeaderDto);
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("ad-page-chp")
    public Page<Advertisement> getPageAdsByCategoryAndHeaderPriceDto(@Valid @RequestBody CategoryHeaderPriceDto chpDto) {
        return ADS_SERVICE.getByCategoryHeaderPriceDto(chpDto);
    }
}


