package com.service.impl;

import com.dao.UserDAO;
import com.entity.User;
import com.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements UserService {

    final UserDAO USER_DAO;

    @Override
    public void save(User user) {

        USER_DAO.save(user);
    }

    @Override
    public User findById(int id) {
        return USER_DAO.findById(id).get();
    }

    @Override
    public void update(User user) {
        USER_DAO.save(user);
    }

    @Override
    public void deleteById(int id) {
        USER_DAO.deleteById(id);
    }

    @Override
    public int countAll() {
        return Math.toIntExact(USER_DAO.count());
    }

    @Override
    public List<User> findAllOrderByRegistration(int startRow, int amount) {

        PageRequest pr = PageRequest.of(startRow, amount, Sort.by("registration"));

        return USER_DAO.findAll(pr).toList();

    }
}
