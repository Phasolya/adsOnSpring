package com.controller;

import com.domain.MatchingAd;
import com.service.MatchingAdService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor

@RestController
@RequestMapping("mads/")
public class MatchingAdController {

    final MatchingAdService MATCHING_AD_SERVICE;

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("mad")
    public void save(@Valid @RequestBody MatchingAd mad) {
        MATCHING_AD_SERVICE.save(mad);
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("mad/{id}")
    public MatchingAd findById( @PathVariable("id") int id) {
        return MATCHING_AD_SERVICE.find(id);
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @PutMapping("mad")
    public void update(@Valid @RequestBody MatchingAd mad) {
        MATCHING_AD_SERVICE.update(mad);
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("mad/{id}")
    public void deleteById( @PathVariable("id") int id) {
        MATCHING_AD_SERVICE.delete(id);
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("by-user{id}")
    public List<MatchingAd> getByUser(@PathVariable("id") int userId) {

        return MATCHING_AD_SERVICE.getByUserOrderById(userId);

    }
}
