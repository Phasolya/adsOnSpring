package com.repository;


import com.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * {@link MailAddressRepository} interface serves to simplify the data access
 * process for {@link Email} email adresses in database,binds database to implementation
 * part,has extends CRUD methods from {@link JpaRepository} and queries for
 * getting need additional data from database.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
public interface MailAddressRepository extends JpaRepository<Email, Integer> {

    @Query("SELECT ma.user.email.mailAddress " +
            "FROM MatchingAd ma " +
            "WHERE ma.category.id = :categoryId " +
            "AND :adsPrice BETWEEN ma.priceFrom AND ma.priceTo " +
            "AND :title LIKE CONCAT('%',ma.title,'%')")
    List<String> findAllSuitableEmails(@Param("categoryId") int categoryId,
                                       @Param("adsPrice") BigDecimal asdPrice, @Param("title") String title);
}
