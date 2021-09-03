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

        manager.persist(user);

    }

    @Override
    public User findById(int id) {

        return manager.find(User.class, id);

    }

    @Override
    public void update(User user) {

        User merged = manager.merge(user);

        manager.persist(merged);

    }

    @Override
    public void deleteById(int id) {

        User reference = manager.getReference(User.class, id);

        manager.remove(reference);

    }

    @Override
    public int countAll() {

        Query query = manager.createQuery("SELECT COUNT(u.id) FROM User u ", Long.class);

        return ((Long) query.getSingleResult()).intValue();

    }

    @Override
    public List<User> findAll() {

        Query query = manager.createQuery("FROM User u ORDER BY u.login", User.class);

        List<User> resultList = query.getResultList();

        return resultList;
    }

}
