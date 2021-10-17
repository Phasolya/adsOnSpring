package com.repository;


import com.domain.MatchingAd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * {@link MatchingAdRepository} interface serves to simplify the data access
 * process for {@link MatchingAd} in database,binds database to implementation
 * part,has extends CRUD methods from {@link JpaRepository}.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
public interface MatchingAdRepository extends JpaRepository<MatchingAd, Integer> {

    List<MatchingAd> getByUserIdOrderById(int userId);
}
