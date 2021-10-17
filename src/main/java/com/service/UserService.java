package com.service;

import com.domain.User;

import java.util.List;

/**
 * {@link UserService} interface binds realization part
 * with user and binds {@link com.dao.UserDao} layer.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
public interface UserService extends CrudService<User> {

    User findByLogin(String login);

    int countAll();

    List<User> findAllOrderByRegistration(int startRow, int amount);

}
