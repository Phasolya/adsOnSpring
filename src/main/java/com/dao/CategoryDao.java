package com.dao;

import com.entity.Category;

import java.util.List;

public interface CategoryDao {

    void save(Category category);

    Category findById(int id);

    void update(Category category);

    void deleteById(int id);

    int countAll();

    List<Category> getSortedByName(int startRow, int amount);

}
