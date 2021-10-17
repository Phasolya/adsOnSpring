package com.service;

import com.domain.Category;

import java.util.List;

/**
 * {@link CategoryService} interface binds realization part
 * with user and binds {@link com.dao.CategoryDao} layer.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
public interface CategoryService extends CrudService<Category> {

    int countAll();

    List<Category> getSortedByName(int startRow, int amount);

}

