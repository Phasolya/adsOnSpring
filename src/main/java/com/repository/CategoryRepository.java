package com.repository;


import com.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {@link CategoryRepository} interface serves to simplify the data access
 * process for {@link Category} in database,binds database to implementation
 * part,has extends CRUD methods from {@link JpaRepository}.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
