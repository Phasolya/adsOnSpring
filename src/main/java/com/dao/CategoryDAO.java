package com.dao;

import com.entity.Category;

import java.util.List;

public interface CategoryDAO extends CrudDAO<Category> {

    int countAll();

    List<Category> selectAllSortedByName();

    List<Category> selectSortedByName(int startRow, int amount);

}
