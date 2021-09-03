package com.controller;

import com.entity.User;
import com.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)

@Controller
@RestController
@RequestMapping("users/")
public class UserController {

    final UserService USER_SERVICE;

    public UserController(UserService userService) {
        USER_SERVICE = userService;
    }

    @PutMapping("save")
    public void save(@RequestBody User user) {

        USER_SERVICE.save(user);

    }

    @GetMapping("find/{id}")
    public User findById(@PathVariable("id") int id) {

        return USER_SERVICE.findById(id);

    }

    @PutMapping("update")
    public void update(@RequestBody User user) {

        USER_SERVICE.save(user);

    }

    @PutMapping("delete/{id}")
    public void deleteById(@PathVariable("id") int id) {

        USER_SERVICE.deleteById(id);

    }

    @GetMapping("count-all")
    public int countAll() {

        return USER_SERVICE.countAll();

    }

    @GetMapping("select-all")
    public List<User> selectAll() {

        return USER_SERVICE.selectAll();

    }


}
