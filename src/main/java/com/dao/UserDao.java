package com.dao;

import com.domain.User;

import java.util.List;

/**
 * {@link UserDao} interface serves the data access process for {@link User}
 * in database for crud operations and other operations.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
public interface UserDao extends CrudDao<User> {

    User findByLogin(String login);

    int countAll();

    /**
     * Method return Users ordered by Registraion
     * */
    List<User> findAll(int startRow, int amount);

}
