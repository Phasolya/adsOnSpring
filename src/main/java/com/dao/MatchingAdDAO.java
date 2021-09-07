package com.dao;

import com.entity.MatchingAd;
import com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchingAdDAO extends JpaRepository<MatchingAd, Integer> {

    List<MatchingAd> getByUserOrderById(User user);

}
