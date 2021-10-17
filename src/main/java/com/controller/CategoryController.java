package com.controller;


import com.domain.Category;
import com.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor

@RestController
@RequestMapping("categories/")
public class CategoryController {

    final CategoryService CATEGORY_SERVICE;

    @Secured(value = "ROLE_ADMIN")
    @PostMapping("category")
    public void save(@Valid @RequestBody Category category) { CATEGORY_SERVICE.save(category); }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("category/{id}")
    public Category findById(@PathVariable("id") int id) {
        return CATEGORY_SERVICE.find(id);
    }

    @Secured(value = "ROLE_ADMIN")
    @PutMapping("category")
    public void update(@Valid @RequestBody Category category) {
        CATEGORY_SERVICE.update(category);
    }
    @Secured(value = "ROLE_ADMIN")
    @DeleteMapping("category/{id}")
    public void deleteById(@PathVariable("id") int id) {
        CATEGORY_SERVICE.delete(id);
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("count")
    public int countAll() {
        return CATEGORY_SERVICE.countAll();
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("all/from{startRow}amount{amount}")
    public List<Category> getSortedByName(@PathVariable("startRow") int startRow,
                                          @PathVariable("amount") int amount) {

        return CATEGORY_SERVICE.getSortedByName(startRow, amount);

    }
}
