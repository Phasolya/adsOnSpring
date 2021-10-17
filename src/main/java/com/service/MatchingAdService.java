package com.service;

import com.domain.MatchingAd;

import java.util.List;

/**
 * {@link MatchingAdService} interface binds realization part
 * with user and binds {@link com.dao.MatchingAdDao} layer.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
public interface MatchingAdService extends CrudService<MatchingAd> {

    List<MatchingAd> getByUserOrderById(int userId);

}
