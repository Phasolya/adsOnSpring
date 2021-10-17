package com.dao.impl;

import com.dao.UserDao;
import com.domain.User;
import com.repository.UserRepository;
import com.util.Util;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link UserDaoImpl} class serves for the data access process for
 * {@link User} in database has extends CRUD methods,and other methods
 * for getting need additional data from database.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    /**
     * This is object instance of {@link UserRepository}
     * helps us persist data into data base with Spring Data Jpa.
     */
    final UserRepository USER_REPOSITORY;

    @Override
    public void save(User user) {
        USER_REPOSITORY.save(user);
    }

    @Override
    public User find(int id) {
        return USER_REPOSITORY.findById(id).get();
    }

    @Override
    public User findByLogin(String login) {
        return USER_REPOSITORY.findByLogin(login);
    }

    @Override
    public void update(User user) {
        USER_REPOSITORY.save(user);
    }

    @Override
    public void delete(int id) {
        USER_REPOSITORY.deleteById(id);
    }

    @Override
    public int countAll() {
        return Math.toIntExact(USER_REPOSITORY.count());
    }

    @Override
    public List<User> findAll(int startRow, int amount) {

        PageRequest pr = PageRequest.of(startRow, amount, Sort.by(Util.SORT_TYPE_REGISTRATION));

        return USER_REPOSITORY.findAll(pr).toList();

    }
}
