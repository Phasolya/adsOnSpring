package com.service.impl;

import com.dao.MatchingAdDao;
import com.entity.MatchingAd;
import com.entity.User;
import com.service.MatchingAdService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class MatchingAdServiceImpl implements MatchingAdService {

    final MatchingAdDao MATCHING_AD_DAO;

    @Override
    public void save(MatchingAd matchingAd) {
        MATCHING_AD_DAO.save(matchingAd);
    }

    @Override
    public MatchingAd findById(int id) {
        return MATCHING_AD_DAO.findById(id);
    }

    @Override
    public void update(MatchingAd matchingAd) {
        MATCHING_AD_DAO.update(matchingAd);
    }

    @Override
    public void deleteById(int id) { MATCHING_AD_DAO.deleteById(id); }

    @Override
    public List<MatchingAd> getByUserOrderById(int userId) {
        return MATCHING_AD_DAO.getByUser(userId);
    }
}
