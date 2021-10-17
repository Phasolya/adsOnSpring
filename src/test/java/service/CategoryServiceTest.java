package service;

import com.domain.Category;
import com.repository.CategoryRepository;
import com.service.CategoryService;
import config.ConfigAppTest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import util.EntityGenerator;


import java.util.List;

/**
 * This is a class for testing the class of the
 * {@link CategoryService} and its methods.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@SpringJUnitConfig(ConfigAppTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = {
        "classpath:scripts/truncate_advertisements_table.sql",
        "classpath:scripts/truncate_categories_table.sql",
        "classpath:scripts/truncate_users_table.sql",
        "classpath:scripts/truncate_emails_table.sql",
        "classpath:scripts/truncate_phones_table.sql",
        "classpath:scripts/truncate_roles_table.sql",
        "classpath:scripts/truncate_addresses_table.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WebAppConfiguration
public class CategoryServiceTest {

    @Autowired
    CategoryRepository repository;

    @Autowired
    CategoryService service;

    @Test
    public void shouldSaveCategory() {

        Category category = EntityGenerator.generateCategory(0);

        service.save(category);

        Category byId = repository.findById(category.getId()).get();

        Assert.assertEquals(category, byId);

    }

    @Test
    public void shouldFindCategoryById() {

        Category category = EntityGenerator.generateCategory(0);

        repository.save(category);

        Category byId = service.find(category.getId());

        Assert.assertEquals(category, byId);

    }

    @Test
    public void shouldUpdateCategory() {

        Category category = EntityGenerator.generateCategory(0);

        category.setName("updated");

        service.update(category);

        Category byId = repository.findById(category.getId()).get();

        Assert.assertEquals(category, byId);

    }

    @Test
    public void shouldDeleteCategoryById() {

        Category category = EntityGenerator.generateCategory(0);

        repository.save(category);

        service.delete(category.getId());

        Assert.assertEquals(repository.count(), 0);

    }

    @Test
    public void shouldCountAlCategories() {

        Category category = EntityGenerator.generateCategory(0);

        repository.save(category);

        int count = service.countAll();

        Assert.assertEquals(count, 1);

    }

    @Test
    public void shouldFindAllSortedByName() {

        Category category = EntityGenerator.generateCategory(0);

        repository.save(category);

        List<Category> categories = service.getSortedByName(0, 1);

        Assert.assertEquals(categories.size(), 1);

        Assert.assertEquals(category, categories.get(0));

    }
}
