package com.service;

import com.entity.MatchingAd;
import com.entity.User;

import java.util.List;

public interface MatchingAdService {

    void save(MatchingAd matchingAd);

    MatchingAd findById(int id);

    void update(MatchingAd matchingAd);

    void deleteById(int id);

    List<MatchingAd> getByUserOrderById(User user);

}
