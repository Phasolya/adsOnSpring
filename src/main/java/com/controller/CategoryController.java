package com.controller;

import com.entity.Category;
import com.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor

@Validated
@RestController
@RequestMapping("categories/")
public class CategoryController {

    final CategoryService CATEGORY_SERVICE;

    @PostMapping("category")
    public void save(@Valid @RequestBody Category category) {
        CATEGORY_SERVICE.save(category);
    }

    @GetMapping("category/{id}")
    public Category findById(@PathVariable("id") int id) {
        return CATEGORY_SERVICE.findById(id);
    }

    @PutMapping("category")
    public void update(@Valid @RequestBody Category category) {
        CATEGORY_SERVICE.update(category);
    }

    @DeleteMapping("category/{id}")
    public void deleteById(@PathVariable("id") int id) {
        CATEGORY_SERVICE.deleteById(id);
    }

    @GetMapping("count")
    public int countAll() { return CATEGORY_SERVICE.countAll(); }

    @GetMapping("all/from{startRow}amount{amount}")
    public List<Category> getSortedByName(@Min(0) @PathVariable("startRow") int startRow,
                                          @Max(20) @PathVariable("amount") int amount) {

        return CATEGORY_SERVICE.getSortedByName(startRow, amount);

    }

}
