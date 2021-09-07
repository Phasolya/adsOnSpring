package com.dao;

import com.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MailAddressDAO extends JpaRepository<Email, Integer> {

    @Query("SELECT ma.user.email.mailAddress " +
            "FROM MatchingAd ma " +
            "WHERE ma.category.id = :categoryId " +
            "AND ma.priceFrom <= :adsPrice " +
            "AND ma.priceTo >= :adsPrice " +
            "AND ma.title LIKE CONCAT('%',:title,'%')")
    List<String> findAllSuitableEmails(@Param("categoryId") int categoryId, @Param("adsPrice") BigDecimal asdPrice, @Param("title") String title);

//    @Query("SELECT ma.user.email.mailAddress " +
//            "FROM MatchingAd ma " +
//            "WHERE ma.category.id = :categoryId " +
//            "AND ma.priceFrom <= :adsPrice " +
//            "AND ma.priceTo >= :adsPrice " +
//            "AND ma.title NOT LIKE CONCAT('%',:title, '%')")
//    List<String> findAllSuitableEmails(@Param("categoryId") int categoryId, @Param("adsPrice") BigDecimal asdPrice, @Param("title") String title);

}
