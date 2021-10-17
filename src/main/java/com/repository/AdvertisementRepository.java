package com.repository;

import com.domain.Advertisement;
import com.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * {@link AdvertisementRepository} interface serves to simplify the data access
 * process for {@link Advertisement} in database,binds database to implementation
 * part,has extends CRUD methods from {@link JpaRepository} and queries for
 * getting need additional data from database.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Advertisement a WHERE a.isActive = false ")
    void deleteAllByIsActiveFalse();

    //todo: tests dont work without Transactional annotation
    @Transactional
    void deleteAllByUser(User user);

    Page<Advertisement> getByUser(User user, Pageable pageable);

    Page<Advertisement> getByHeaderContains(String keyWord, Pageable pageable);

    Page<Advertisement> getByCategoryId(int categoryId, Pageable pageable);

    Page<Advertisement> getByCategoryIdAndHeader(int categoryId, String title, Pageable pageable);

    Page<Advertisement> getByCategoryIdAndHeaderAndPriceBetween(int categoryId, String title,
                                                                BigDecimal priceFrom, BigDecimal priceTo,
                                                                Pageable pageable);

}
