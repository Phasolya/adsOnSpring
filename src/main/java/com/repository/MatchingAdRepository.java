package com.repository;

import com.entity.MatchingAd;
import com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchingAdRepository extends JpaRepository<MatchingAd, Integer> {

    List<MatchingAd> getByUserIdOrderById(int userId);
}
