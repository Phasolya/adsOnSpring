package com.dao;

import com.domain.MatchingAd;

import java.util.List;

/**
 * {@link MatchingAdDao} interface serves the data access process for {@link MatchingAd}
 * in database for crud operations and other operations.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
public interface MatchingAdDao extends CrudDao<MatchingAd>{

    /**
     * Method return MatchingAd ordered by MatchingAd.id
     * */
    List<MatchingAd> getByUser(int userId);

}
