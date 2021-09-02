package com.service.impl;

import com.dao.CategoryDAO;
import com.entity.Category;
import com.service.CategoryService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CategoryServiceImpl implements CategoryService {

    final CategoryDAO CATEGORY_DAO;

    public CategoryServiceImpl(@Autowired CategoryDAO category_dao) {
        CATEGORY_DAO = category_dao;
    }

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
        return CATEGORY_DAO.countAll();
    }

    @Override
    public List<Category> selectAllSortedByName() {
        return CATEGORY_DAO.selectAllSortedByName();
    }

    @Override
    public List<Category> selectSortedByName(int startRow, int amount) {
        return CATEGORY_DAO.selectSortedByName(startRow, amount);
    }

}
