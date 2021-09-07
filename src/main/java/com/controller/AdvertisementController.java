package com.controller;

import com.entity.Advertisement;
import com.entity.Category;
import com.service.AdvertisementService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

@RestController
@RequestMapping("ads/")
public class AdvertisementController {

    final AdvertisementService ADS_SERVICE;

    //localhost:9999/fakeOlx/ads/save
    @PutMapping("ad")
    public void save(@RequestBody Advertisement advertisement) {
        ADS_SERVICE.saveAndSentNotifications(advertisement);
    }

    @PutMapping("update")
    public void update(@RequestBody Advertisement advertisement) { ADS_SERVICE.updateAndSentNotifications(advertisement); }

    @GetMapping("find/{id}")
    public Advertisement findById(@PathVariable int id) {
        return ADS_SERVICE.findById(id);
    }

    @PutMapping("delete/{id}")
    public void deleteById(@PathVariable("id") int id) { ADS_SERVICE.deleteById(id); }

    @GetMapping("count-all")
    public int countAll() { return ADS_SERVICE.countAll(); }

    @PostMapping("count-by-category")
    public int countByCategory(Category category) { return ADS_SERVICE.countByCategory(category); }

    @PostMapping("count-by-category/{title}")
    public int countByCategoryAndHeader(Category category, @PathVariable String title) {
        return ADS_SERVICE.countByCategoryAndHeader(category, title);
    }

    @PostMapping("count-by-category/{title}/from{priceFrom}to{priceTo}")
    public int countByCategoryAndHeaderWithPrice(Category category, @PathVariable(value = "title") String title,
                                        @PathVariable(value = "priceFrom") BigDecimal priceFrom,
                                        @PathVariable(value = "priceTo") BigDecimal priceTo) {
        return ADS_SERVICE.countByCategoryAndHeaderAndPriceBetween(category, title, priceFrom, priceTo);
    }

    @PostMapping("list/{categoryId}/from{priceFrom}to{priceTo}")
    public int countByCategoryAndHeader(Category category, @PathVariable(value = "title") String title,
                                        @PathVariable(value = "priceFrom") BigDecimal priceFrom,
                                        @PathVariable(value = "priceTo") BigDecimal priceTo) {
        return ADS_SERVICE.countByCategoryAndHeaderAndPriceBetween(category, title, priceFrom, priceTo);
    }
}

//    // ===============================================================================================================
//
//    List<Advertisement> getByCategoryOrderByPrice(Category category, int startRow, int amount);
//
//    List<Advertisement> getByCategoryOrderByPriceDesc(Category category, int startRow, int amount);
//
