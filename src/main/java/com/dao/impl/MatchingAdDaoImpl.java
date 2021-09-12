package com.dao.impl;

import com.dao.MatchingAdDao;
import com.entity.MatchingAd;
import com.entity.User;
import com.repository.MatchingAdRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchingAdDaoImpl implements MatchingAdDao {

    final MatchingAdRepository MATCHING_AD_REPOSITORY;

    @Override
    public void save(MatchingAd matchingAd) {
        MATCHING_AD_REPOSITORY.save(matchingAd);
    }

    @Override
    public MatchingAd findById(int id) {
        return MATCHING_AD_REPOSITORY.findById(id).get();
    }

    @Override
    public void update(MatchingAd matchingAd) {
        MATCHING_AD_REPOSITORY.save(matchingAd);
    }

    @Override
    public void deleteById(int id) {
        MATCHING_AD_REPOSITORY.deleteById(id);
    }

    @Override
    public List<MatchingAd> getByUser(int userId) {
        return MATCHING_AD_REPOSITORY.getByUserIdOrderById(userId);
    }
}
