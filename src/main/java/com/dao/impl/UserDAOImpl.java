package com.dao.impl;

import com.dao.UserDAO;
import com.entity.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    EntityManager manager;

    @Override
    public void save(User user) {

        System.out.println("BEFORE=================: " + user.getId());

        manager.persist(user);

        System.out.println("AFTER===================: " + user.getId());

    }

    @Override
    public User findById(int id) {

        return manager.find(User.class, id);

    }

    @Override
    public void update(User user) {

        manager.merge(user);

        manager.persist(user);

    }

    @Override
    public void deleteById(int id) {

        User reference = manager.getReference(User.class, id);

        manager.remove(reference);

    }

    @Override
    public int countAll() {

        int result;

        Query query = manager.createQuery("SELECT COUNT(u.id) FROM User u ", Long.class);

        result = (int) query.getSingleResult();

        return result;
    }

    @Override
    public List<User> findAll() {

        Query query = manager.createQuery("FROM User u ORDER BY u.login", User.class);

        List<User> resultList = query.getResultList();

        return resultList;
    }

}