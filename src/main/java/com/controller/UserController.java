package com.controller;


import com.domain.User;
import com.service.UserService;
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
@RequestMapping("users/")
public class UserController {

    final UserService USER_SERVICE;

    @PostMapping("user")
    public void save(@Valid @RequestBody User user) {
        USER_SERVICE.save(user);
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("user/{id}")
    public User findById(@PathVariable("id") int id) {
        return USER_SERVICE.find(id);
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @PutMapping("user")
    public void update(@Valid @RequestBody User user) {
        USER_SERVICE.update(user);
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("user/{id}")
    public void deleteById(@PathVariable("id") int id) {
        USER_SERVICE.delete(id);
    }

    @Secured(value = "ROLE_ADMIN")
    @GetMapping("count")
    public int countAll() {
        return USER_SERVICE.countAll();
    }

    @Secured(value = "ROLE_ADMIN")
    @GetMapping("from{startRow}amount{amount}")
    public List<User> selectAll(@PathVariable("startRow") int startRow,
                                @PathVariable("amount") int amount) {

        return USER_SERVICE.findAllOrderByRegistration(startRow, amount);

    }
}
