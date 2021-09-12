package com.service.impl;

import com.dao.UserDao;
import com.entity.User;
import com.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements UserService {

    final UserDao USER_DAO;

    @Override
    public void save(User user) { USER_DAO.save(user); }

    @Override
    public User findById(int id) {
        return USER_DAO.findById(id);
    }

    @Override
    public void update(User user) {
        USER_DAO.update(user);
    }

    @Override
    public void deleteById(int id) {
        USER_DAO.deleteById(id);
    }

    @Override
    public int countAll() {
        return Math.toIntExact(USER_DAO.countAll());
    }

    @Override
    public List<User> findAllOrderByRegistration(int startRow, int amount) { return USER_DAO.findAll(startRow, amount); }
}
