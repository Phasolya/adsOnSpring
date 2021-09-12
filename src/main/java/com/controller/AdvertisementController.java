package com.controller;

import com.dto.CategoryDto;
import com.dto.CategoryHeaderDto;
import com.dto.CategoryHeaderPriceDto;
import com.entity.Advertisement;
import com.service.AdvertisementService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

@RestController
@RequestMapping("ads/")
public class AdvertisementController {

    final AdvertisementService ADS_SERVICE;

    //localhost:9999/fakeOlx/ads/save
    @PostMapping("ad")
    public void save(@Valid @RequestBody Advertisement advertisement) {
        ADS_SERVICE.saveAndSentNotifications(advertisement);
    }

    @GetMapping("ad/{id}")
    public Advertisement findById(@PathVariable int id) {
        return ADS_SERVICE.findById(id);
    }

    @PutMapping("ad")
    public void update(@Valid @RequestBody Advertisement advertisement) {
        ADS_SERVICE.updateAndSentNotifications(advertisement);
    }

    @DeleteMapping("ad/{id}")
    public void deleteById(@PathVariable("id") int id) {
        ADS_SERVICE.deleteById(id);
    }

    @GetMapping("count")
    public int countAll() {
        return ADS_SERVICE.countAll();
    }

    @PostMapping("ad-page")
    public Page<Advertisement> countByCategoryDto(@Valid @RequestBody CategoryDto categoryDto) {
        return ADS_SERVICE.getByCategoryDto(categoryDto);
    }

    @PostMapping("ad-page")
    public Page<Advertisement> countByCategoryAndHeaderDto(@Valid @RequestBody CategoryHeaderDto categoryHeaderDto) {
        return ADS_SERVICE.getByCategoryHeaderDto(categoryHeaderDto);
    }

    @PostMapping("ad-page")
    public Page<Advertisement> countByCategoryAndHeaderPriceDto(@Valid @RequestBody CategoryHeaderPriceDto chpDto) {
        return ADS_SERVICE.getByCategoryHeaderPriceDto(chpDto);
    }
}


