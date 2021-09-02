package com.service;

import com.entity.Category;
import java.util.List;

public interface CategoryService {

    void save(Category category);

    Category findById(int id);

    void update(Category category);

    void deleteById(int id);

    int countAll();

    List<Category> selectAllSortedByName();

    List<Category> selectSortedByName(int startRow, int amount);

}

