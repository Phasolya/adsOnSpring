package com.dao;

import com.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface UserDao {

    void save(User user);

    User findById(int id);

    void update(User user);

    void deleteById(int id);

    int countAll();

    /**
     * Method return Users ordered by Registraion
     * */
    List<User> findAll(int startRow, int amount);

}
