package com.service;

import com.entity.Advertisement;
import com.entity.Category;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface AdvertisementService {

    void saveAndSentNotifications(Advertisement advertisement);

    Advertisement findById(int id);

    void updateAndSentNotifications(Advertisement advertisement);

    void deleteById(int id);

    int countAll();

    // ===============================================================================================================

    int countByCategory(Category category);

    List<Advertisement> getByCategoryOrderByPrice(Category category, int startRow, int amount);

    List<Advertisement> getByCategoryOrderByPriceDesc(Category category, int startRow, int amount);

    // ===============================================================================================================

    int countByCategoryAndHeader(Category category, String title);

    List<Advertisement> getByCategoryAndHeaderOrderByPrice(Category category, String title, int startRow, int amount);

    List<Advertisement> getByCategoryAndHeaderOrderByPriceDesc(Category category, String title,
                                                               int startRow, int amount);

    // ===============================================================================================================

    int countByCategoryAndHeaderAndPriceBetween(Category category, String title,
                                                BigDecimal priceFrom, BigDecimal priceTo);

    List<Advertisement> getByCategoryAndHeaderAndPriceBetweenOrderByPrice(Category category, String title,
                                                                          BigDecimal priceFrom, BigDecimal priceTo,
                                                                          int startRow, int amount);

    List<Advertisement> getByCategoryAndHeaderAndPriceBetweenOrderByPriceDesc(Category category, String title,
                                                                              BigDecimal priceFrom, BigDecimal priceTo,
                                                                              int startRow, int amount);

}
