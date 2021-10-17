package com.service.impl;

import com.dao.UserDao;
import com.domain.User;
import com.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@link UserServiceImpl} class binds realization part with user
 * and binds {@link UserDao} layer.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    final UserDao USER_DAO;

    final PasswordEncoder ENCODER;

    @Override
    public void save(User user) {

        String password = user.getPassword();

        user.setPassword(ENCODER.encode(password));

        USER_DAO.save(user);
    }

    @Override
    public User find(int id) {
        return USER_DAO.find(id);
    }

    @Override
    public User findByLogin(String login) {
        return USER_DAO.findByLogin(login);
    }

    @Override
    public void update(User user) {
        USER_DAO.update(user);
    }

    @Override
    public void delete(int id) {
        USER_DAO.delete(id);
    }

    @Override
    public int countAll() {
        return Math.toIntExact(USER_DAO.countAll());
    }

    @Override
    public List<User> findAllOrderByRegistration(int startRow, int amount) {
        return USER_DAO.findAll(startRow, amount);
    }
}
