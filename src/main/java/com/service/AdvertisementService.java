package com.service;

import com.entity.Advertisement;
import com.entity.Category;

import java.math.BigDecimal;
import java.util.List;

public interface AdvertisementService {

    void save(Advertisement advertisement);

    Advertisement findById(int id);

    void update(Advertisement advertisement);

    void deleteById(int id);

    int countAll();

    List<Advertisement> selectAll();

    // ===============================================================================================================

    int countBy(Category category );

    List<Advertisement> selectByPriceUp(Category category, int startRow, int amount);

    List<Advertisement> selectByPriceDown(Category category, int startRow, int amount);

    // ===============================================================================================================

    int countBy(Category category, String title );

    List<Advertisement> selectByPriceUp(Category category, String title, int startRow, int amount);

    List<Advertisement> selectByPriceDown(Category category, String title, int startRow, int amount);

    // ===============================================================================================================

    int countBy(Category category, String title, BigDecimal priceFrom, BigDecimal priceTo);

    List<Advertisement> selectByPriceUp(Category category, String title, BigDecimal priceFrom, BigDecimal priceTo, int startRow, int amount);

    List<Advertisement> selectByPriceDown(Category category, String title, BigDecimal priceFrom, BigDecimal priceTo, int startRow, int amount);

}
