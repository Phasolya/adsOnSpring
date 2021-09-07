package com.dao;

import com.entity.Advertisement;
import com.entity.Category;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AdvertisementDAO extends JpaRepository<Advertisement, Integer> {

    int countByCategory(Category category);

    List<Advertisement> getByCategoryOrderByPrice(Category category, Pageable pageable);

    List<Advertisement> getByCategoryOrderByPriceDesc(Category category, Pageable pageable);

    // ===============================================================================================================

    int countByCategoryAndHeader(Category category, String title);

    List<Advertisement> getByCategoryAndHeaderOrderByPrice(Category category, String title, Pageable pageable);

    List<Advertisement> getByCategoryAndHeaderOrderByPriceDesc(Category category, String title, Pageable pageable);

    // ===============================================================================================================

    int countByCategoryAndHeaderAndPriceBetween(Category category, String title, BigDecimal priceFrom, BigDecimal priceTo);

    List<Advertisement> getByCategoryAndHeaderAndPriceBetweenOrderByPrice(Category category, String title, BigDecimal priceFrom, BigDecimal priceTo, Pageable pageable);

    List<Advertisement> getByCategoryAndHeaderAndPriceBetweenOrderByPriceDesc(Category category, String title, BigDecimal priceFrom, BigDecimal priceTo, Pageable pageable);
}
