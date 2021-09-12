package com.service.impl;

import com.dao.CategoryDao;
import com.entity.Category;
import com.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Category findById(int id) {
        return CATEGORY_DAO.findById(id);
    }

    @Override
    public void update(Category category) {
        CATEGORY_DAO.update(category);
    }

    @Override
    public void deleteById(int id) {
        CATEGORY_DAO.deleteById(id);
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
