package com.dao;

public interface CrudDAO <T> {

    void save(T t);

    T findById(int id);

    void update(T t);

    void deleteById( int id);

}
