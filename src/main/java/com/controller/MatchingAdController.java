package com.controller;

import com.entity.MatchingAd;
import com.entity.User;
import com.service.MatchingAdService;
import com.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor

@RestController
@RequestMapping("mads/")
public class MatchingAdController {

    final MatchingAdService MATCHING_AD_SERVICE;

    @PostMapping("mad")
    public void save(@Valid @RequestBody MatchingAd mad) {
        MATCHING_AD_SERVICE.save(mad);
    }

    @GetMapping("mad/{id}")
    public MatchingAd findById( @PathVariable("id") int id) {
        return MATCHING_AD_SERVICE.findById(id);
    }

    @PutMapping("mad")
    public void update(@Valid @RequestBody MatchingAd mad) {
        MATCHING_AD_SERVICE.update(mad);
    }

    @DeleteMapping("mad/{id}")
    public void deleteById( @PathVariable("id") int id) {
        MATCHING_AD_SERVICE.deleteById(id);
    }

    @GetMapping("by-user{id}")
    public List<MatchingAd> getByUser( @PathVariable("id") int userId) {

        return MATCHING_AD_SERVICE.getByUserOrderById(userId);

    }
}
