package com.repository;

import com.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MailAddressRepository extends JpaRepository<Email, Integer> {

    @Query("SELECT ma.user.email.mailAddress " +
            "FROM MatchingAd ma " +
            "WHERE ma.category.id = :categoryId " +
            "AND :adsPrice BETWEEN ma.priceFrom AND ma.priceTo " +
            "AND :title LIKE CONCAT('%',ma.title,'%')")
    List<String> findAllSuitableEmails(@Param("categoryId") int categoryId, @Param("adsPrice") BigDecimal asdPrice, @Param("title") String title);
}
