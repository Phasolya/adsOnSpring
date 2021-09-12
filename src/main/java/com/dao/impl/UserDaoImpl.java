package com.dao.impl;

import com.dao.UserDao;
import com.entity.User;
import com.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    final UserRepository USER_REPOSITORY;

    @Override
    public void save(User user) {
        USER_REPOSITORY.save(user);
    }

    @Override
    public User findById(int id) {
        return USER_REPOSITORY.findById(id).get();
    }

    @Override
    public void update(User user) {
        USER_REPOSITORY.save(user);
    }

    @Override
    public void deleteById(int id) {
        USER_REPOSITORY.deleteById(id);
    }

    @Override
    public int countAll() {
        return Math.toIntExact(USER_REPOSITORY.count());
    }

    @Override
    public List<User> findAll(int startRow, int amount) {

        PageRequest pr = PageRequest.of(startRow, amount, Sort.by("registration"));

        return USER_REPOSITORY.findAll(pr).toList();

    }
}
