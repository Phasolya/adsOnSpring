package com.dao;

import com.entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User> {

    int countAll();

    List<User> findAll();
}
