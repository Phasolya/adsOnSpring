package com.controller;

import com.entity.Category;
import com.service.CategoryService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor

@Validated
@RestController
@RequestMapping("categories/")
public class CategoryController {

//    final CategoryService CATEGORY_SERVICE;
//
//    Validator validator;
//
//    @PostMapping("category")
//    public void save(@Valid @RequestBody Category category) {
//
//        Set<ConstraintViolation<Category>> violations = validator.validate(category);
//
//        if (!violations.isEmpty()) {
//            throw new ConstraintViolationException("There are errors", violations);
//        }
//
//        CATEGORY_SERVICE.save(category);
//    }
//
//    @GetMapping("category/{id}")
//    public Category findById(@PathVariable("id") int id) {
//        return CATEGORY_SERVICE.findById(id);
//    }
//
//    @PutMapping("category")
//    public void update(@Valid @RequestBody Category category) {
//        CATEGORY_SERVICE.update(category);
//    }
//
//    @DeleteMapping("category/{id}")
//    public void deleteById(@PathVariable("id") int id) {
//        CATEGORY_SERVICE.deleteById(id);
//    }
//
//    @GetMapping("count")
//    public int countAll() { return CATEGORY_SERVICE.countAll(); }
//
//    @GetMapping("all/from{startRow}amount{amount}")
//    public List<Category> getSortedByName(@Min(0) @PathVariable("startRow") int startRow,
//                                          @Max(20) @PathVariable("amount") int amount) {
//
//        return CATEGORY_SERVICE.getSortedByName(startRow, amount);
//
//    }
//
//    @Autowired
//    public void setValidator(Validator validator) {
//        this.validator = validator;
//    }
}
