package com.dao;

import com.entity.MatchingAd;
import com.entity.User;

import java.util.List;

public interface MatchingAdDao {

    void save(MatchingAd matchingAd);

    MatchingAd findById(int id);

    void update(MatchingAd matchingAd);

    void deleteById(int id);

    /**
     * Method return MatchingAd ordered by MatchingAd.id
     * */
    List<MatchingAd> getByUser(int userId);

}
