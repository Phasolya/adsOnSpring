package com.dao.impl;

import com.dao.CategoryDao;
import com.domain.Category;
import com.repository.CategoryRepository;
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
 * {@link CategoryDaoImpl} class serves for the data access process for
 * {@link Category} in database has extends CRUD methods,and other methods
 * for getting need additional data from database.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CategoryDaoImpl implements CategoryDao {

    /**
     * This is object instance of {@link UserRepository}
     * helps us persist data into data base with Spring Data Jpa.
     */
    final CategoryRepository CATEGORY_REPOSITORY;

    @Override
    public void save(Category category) { CATEGORY_REPOSITORY.save(category); }

    @Override
    public Category find(int id) { return CATEGORY_REPOSITORY.findById(id).get(); }

    @Override
    public void update(Category category) { CATEGORY_REPOSITORY.save(category); }

    @Override
    public void delete(int id) { CATEGORY_REPOSITORY.deleteById(id); }

    @Override
    public int countAll() {
        return Math.toIntExact(CATEGORY_REPOSITORY.count()); }

    @Override
    public List<Category> getSortedByName(int startRow, int amount) {

        PageRequest pr = PageRequest.of(startRow, amount, Sort.by(Util.SORT_TYPE_NAME));

        return CATEGORY_REPOSITORY.findAll(pr).toList();
    }
}
