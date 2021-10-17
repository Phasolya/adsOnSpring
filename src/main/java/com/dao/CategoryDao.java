package com.dao;

import com.domain.Category;

import java.util.List;

/**
 * {@link CategoryDao} interface serves the data access process for {@link Category}
 * in database for crud operations and other operations.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
public interface CategoryDao extends CrudDao<Category>{

    int countAll();

    List<Category> getSortedByName(int startRow, int amount);

}
