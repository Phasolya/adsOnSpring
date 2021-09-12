package com.controller;

import com.entity.User;
import com.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor

@RestController
@RequestMapping("users/")

@Validated
public class UserController {

    final UserService USER_SERVICE;

    @PostMapping("user")
    public void save(@Valid @RequestBody User user) {
        USER_SERVICE.save(user);
    }

    @GetMapping("user/{id}")
    public User findById( @PathVariable("id") int id) {
        return USER_SERVICE.findById(id);
    }

    @PutMapping("user")
    public void update(@Valid @RequestBody User user) {
        USER_SERVICE.update(user);
    }

    @DeleteMapping("user/{id}")
    public void deleteById(@PathVariable("id") int id) {
        USER_SERVICE.deleteById(id);
    }

    @GetMapping("count")
    public int countAll() {
        return USER_SERVICE.countAll();
    }

    @GetMapping("from{startRow}amount{amount}")
    public List<User> selectAll(@Min(0) @PathVariable("startRow") int startRow, @Min(1) @Max(20) @PathVariable("amount") int amount) {

        return USER_SERVICE.findAllOrderByRegistration(startRow, amount);

    }

}
