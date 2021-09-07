package com.service;

import com.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User findById(int id);

    void update(User user);

    void deleteById(int id);

    int countAll();

    List<User> findAllOrderByRegistration(int startRow, int amount);

}
