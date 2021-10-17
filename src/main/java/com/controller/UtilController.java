package com.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor

@RestController
@RequestMapping("fake_olx/")
public class UtilController {

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("home")
    public String defaultPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "welcome to our home page!" + authentication.getName();

    }

}
