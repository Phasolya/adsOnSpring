package com.service;

/**
 * This is interface {@link CrudService} serves the data access process for
 * {@link T} in database for crud operations.
 *
 * @param <T> entity.
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
public interface CrudService<T> {

    void save(T entity);

    T find(int id);

    void update(T entity);

    void delete(int id);
}
