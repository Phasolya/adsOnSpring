package com.dao;

/**
 * This is interface {@link CrudDao} serves the data access process for
 * {@link T} in database for crud operations.
 *
 * @param <T> entity.
 * @author Maxim Vovnianko
 * @version 1.1.
 */
public interface CrudDao<T> {

    void save(T entity);

    T find(int id);

    void update(T entity);

    void delete(int id);
}
