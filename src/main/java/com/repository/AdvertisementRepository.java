package com.repository;

import com.entity.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {

    void deleteAllByIsActiveFalse();

    // ===============================================================================================================

    Page<Advertisement> getByCategoryId(int categoryId, Pageable pageable);

    // ===============================================================================================================

    Page<Advertisement> getByCategoryIdAndHeader(int categoryId, String title, Pageable pageable);

    // ===============================================================================================================

    Page<Advertisement> getByCategoryIdAndHeaderAndPriceBetween(int categoryId, String title,
                                                                            BigDecimal priceFrom, BigDecimal priceTo,
                                                                            Pageable pageable);

}
