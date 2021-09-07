package com.controller;

import com.entity.Category;
import com.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

@RestController
@RequestMapping("categories/")
public class CategoryController {
    final CategoryService CATEGORY_SERVICE;

    @PostMapping("category")
    public void save(@RequestBody Category category) {
        CATEGORY_SERVICE.save(category);
    }

    @GetMapping("category/{id}")
    public Category findById(@PathVariable("id") int id) {
        return CATEGORY_SERVICE.findById(id);
    }

    @PutMapping("category")
    public void update(@RequestBody Category category) {
        CATEGORY_SERVICE.update(category);
    }

    @DeleteMapping("category/{id}")
    public void deleteById(@PathVariable("id") int id) {
        CATEGORY_SERVICE.deleteById(id);
    }

    @GetMapping("count")
    public int countAll() { return CATEGORY_SERVICE.countAll(); }

    @GetMapping("all/from{startRow}amount{amount}")
    public List<Category> getSortedByName(@PathVariable("startRow") int startRow, @PathVariable("amount") int amount) {

        return CATEGORY_SERVICE.getSortedByName(startRow, amount);

    }

}
