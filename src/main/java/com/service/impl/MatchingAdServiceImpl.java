package com.service.impl;

import com.dao.MatchingAdDao;
import com.domain.MatchingAd;
import com.service.MatchingAdService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@link MatchingAdServiceImpl} class binds realization part with user
 * and binds {@link MatchingAdDao} layer.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class MatchingAdServiceImpl implements MatchingAdService {

    final MatchingAdDao MATCHING_AD_DAO;

    @Override
    public void save(MatchingAd matchingAd) {
        MATCHING_AD_DAO.save(matchingAd);
    }

    @Override
    public MatchingAd find(int id) {
        return MATCHING_AD_DAO.find(id);
    }

    @Override
    public void update(MatchingAd matchingAd) {
        MATCHING_AD_DAO.update(matchingAd);
    }

    @Override
    public void delete(int id) { MATCHING_AD_DAO.delete(id); }

    @Override
    public List<MatchingAd> getByUserOrderById(int userId) {
        return MATCHING_AD_DAO.getByUser(userId);
    }
}
