package com.service.impl;

import com.dao.CategoryDao;
import com.domain.Category;
import com.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@link CategoryServiceImpl} class binds realization part with user
 * and binds {@link CategoryDao} layer.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    final CategoryDao CATEGORY_DAO;

    @Override
    public void save(Category category) {
        CATEGORY_DAO.save(category);
    }

    @Override
    public Category find(int id) {
        return CATEGORY_DAO.find(id);
    }

    @Override
    public void update(Category category) {
        CATEGORY_DAO.update(category);
    }

    @Override
    public void delete(int id) {
        CATEGORY_DAO.delete(id);
    }

    @Override
    public int countAll() {
        return Math.toIntExact(CATEGORY_DAO.countAll());
    }


    @Override
    public List<Category> getSortedByName(int startRow, int amount) {

        return CATEGORY_DAO.getSortedByName(startRow, amount);
    }

}
