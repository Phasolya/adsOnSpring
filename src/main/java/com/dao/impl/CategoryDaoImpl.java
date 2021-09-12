package com.dao.impl;

import com.dao.CategoryDao;
import com.entity.Category;
import com.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CategoryDaoImpl implements CategoryDao {

    final CategoryRepository CATEGORY_REPOSITORY;

    @Override
    public void save(Category category) { CATEGORY_REPOSITORY.save(category); }

    @Override
    public Category findById(int id) { return CATEGORY_REPOSITORY.findById(id).get(); }

    @Override
    public void update(Category category) { CATEGORY_REPOSITORY.save(category); }

    @Override
    public void deleteById(int id) { CATEGORY_REPOSITORY.deleteById(id); }

    @Override
    public int countAll() {
        return Math.toIntExact(CATEGORY_REPOSITORY.count()); }

    @Override
    public List<Category> getSortedByName(int startRow, int amount) {

        PageRequest pr = PageRequest.of(startRow, amount, Sort.by("name"));

        return CATEGORY_REPOSITORY.findAll(pr).toList();
    }
}
