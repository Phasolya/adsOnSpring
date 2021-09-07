package com.service.impl;

import com.dao.CategoryDAO;
import com.entity.Category;
import com.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CategoryServiceImpl implements CategoryService {

    final CategoryDAO CATEGORY_DAO;

    @Override
    public void save(Category category) {
        CATEGORY_DAO.save(category);
    }

    @Override
    public Category findById(int id) {
        return CATEGORY_DAO.findById(id).get();
    }

    @Override
    public void update(Category category) {
        CATEGORY_DAO.save(category);
    }

    @Override
    public void deleteById(int id) {
        CATEGORY_DAO.deleteById(id);
    }

    @Override
    public int countAll() {
        return Math.toIntExact(CATEGORY_DAO.count());
    }


    @Override
    public List<Category> getSortedByName(int startRow, int amount) {

        PageRequest pr = PageRequest.of(startRow, amount, Sort.by("name"));

        return CATEGORY_DAO.findAll(pr).toList();
    }

}
