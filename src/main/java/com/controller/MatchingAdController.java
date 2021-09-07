package com.controller;

import com.entity.MatchingAd;
import com.entity.User;
import com.service.MatchingAdService;
import com.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

@RestController
@RequestMapping("mads/")
public class MatchingAdController {

    final MatchingAdService MATCHING_AD_SERVICE;
    final UserService USER_SERVICE;

    @PostMapping("mad")
    public void save(@RequestBody MatchingAd mad) {
        MATCHING_AD_SERVICE.save(mad);
    }

    @GetMapping("mad/{id}")
    public MatchingAd findById(@PathVariable("id") int id) {
        return MATCHING_AD_SERVICE.findById(id);
    }

    @PutMapping("mad")
    public void update(@RequestBody MatchingAd mad) {
        MATCHING_AD_SERVICE.update(mad);
    }

    @DeleteMapping("mad/{id}")
    public void deleteById(@PathVariable("id") int id) {
        MATCHING_AD_SERVICE.deleteById(id);
    }

    @GetMapping("by-user{id}")
    public List<MatchingAd> getByUser(@PathVariable("id") int id) {

        User user = USER_SERVICE.findById(id);

        return MATCHING_AD_SERVICE.getByUserOrderById(user);

    }
}
