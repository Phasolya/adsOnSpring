package com.dao.impl;

import com.dao.MatchingAdDao;
import com.domain.MatchingAd;
import com.repository.MatchingAdRepository;
import com.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link MatchingAdDaoImpl} class serves for the data access process for
 * {@link MatchingAd} in database has extends CRUD methods,and other methods
 * for getting need additional data from database.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchingAdDaoImpl implements MatchingAdDao {

    /**
     * This is object instance of {@link UserRepository}
     * helps us persist data into data base with Spring Data Jpa.
     */
    final MatchingAdRepository MATCHING_AD_REPOSITORY;

    @Override
    public void save(MatchingAd matchingAd) {
        MATCHING_AD_REPOSITORY.save(matchingAd);
    }

    @Override
    public MatchingAd find(int id) {
        return MATCHING_AD_REPOSITORY.findById(id).get();
    }

    @Override
    public void update(MatchingAd matchingAd) {
        MATCHING_AD_REPOSITORY.save(matchingAd);
    }

    @Override
    public void delete(int id) {
        MATCHING_AD_REPOSITORY.deleteById(id);
    }

    @Override
    public List<MatchingAd> getByUser(int userId) {
        return MATCHING_AD_REPOSITORY.getByUserIdOrderById(userId);
    }
}
