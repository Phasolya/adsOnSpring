package com.dao.impl;

import com.dao.CategoryDAO;
import com.entity.Category;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Repository
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

    @PersistenceContext
    EntityManager manager;

    @Override
    public void save(Category category) {

        manager.persist(category);

    }

    @Override
    public Category findById(int id) {

        return manager.find(Category.class, id);

    }

    @Override
    public void update(Category category) {

        Category merged = manager.merge(category);

        manager.persist(merged);

    }

    @Override
    public void deleteById(int id) {

        Category reference = manager.getReference(Category.class, id);

        manager.remove(reference);

    }

    @Override
    public int countAll() {

        int result;

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        CriteriaQuery<Long> cq = criteriaBuilder.createQuery(Long.class);

        cq.select(criteriaBuilder.count(cq.from(Category.class)));

        result = Math.toIntExact(manager.createQuery(cq).getSingleResult());

        return result;
    }

    @Override
    public List<Category> selectAllSortedByName() {

        Query query = manager.createQuery("FROM Category c ORDER BY c.name", Category.class);

        List<Category> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public List<Category> selectSortedByName(int startRow, int amount) {

        Query query = manager.createQuery("FROM Category c ORDER BY c.name", Category.class);

        query.setFirstResult(startRow);

        query.setMaxResults(amount);

        List<Category> resultList = query.getResultList();

        return resultList;

    }
}
