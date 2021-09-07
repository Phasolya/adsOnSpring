package com.controller;

import com.entity.User;
import com.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

@RestController
@RequestMapping("users/")
public class UserController {

    final UserService USER_SERVICE;

    @PostMapping("user")
    public void save(@RequestBody User user) { USER_SERVICE.save(user); }

    @GetMapping("user/{id}")
    public User findById(@PathVariable("id") int id) { return USER_SERVICE.findById(id); }

    @PutMapping("user")
    public void update(@RequestBody User user) { USER_SERVICE.update(user); }

    @DeleteMapping("user/{id}")
    public void deleteById(@PathVariable("id") int id) { USER_SERVICE.deleteById(id); }

    @GetMapping("count")
    public int countAll() { return USER_SERVICE.countAll(); }

    @GetMapping("all/from{startRow}amount{amount}")
    public List<User> selectAll(@PathVariable("startRow") int startRow, @PathVariable("amount") int amount) {

        return USER_SERVICE.findAllOrderByRegistration(startRow, amount);

    }

}
