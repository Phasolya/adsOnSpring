package service;

import com.domain.User;
import com.repository.UserRepository;
import com.service.UserService;
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
 * {@link UserService} and its methods.
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
        "classpath:scripts/truncate_matching_ads_table.sql",
        "classpath:scripts/truncate_phones_table.sql",
        "classpath:scripts/truncate_roles_table.sql",
        "classpath:scripts/truncate_addresses_table.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    UserRepository repository;

    @Autowired
    UserService service;

    @Test
    public void shouldSaveUser() {

        User user = EntityGenerator.generateUser(0);

        String login = user.getLogin();

        service.save(user);

        User byId = repository.findByLogin(login);

        Assert.assertEquals(byId, user);

    }

    @Test
    public void shouldFindUserById() {

        User user = EntityGenerator.generateUser(0);

        repository.save(user);

        User byId = service.find(user.getId());

        Assert.assertEquals(byId, user);

    }

    @Test
    public void shouldUpdateUser() {

        User user = EntityGenerator.generateUser(0);

        user.setLogin("updated");

        service.update(user);

        User byId = repository.findById(user.getId()).get();

        Assert.assertEquals(byId, user);

    }

    @Test
    public void shouldDeleteUserById() {

        User user = EntityGenerator.generateUser(0);

        repository.save(user);

        service.delete(user.getId());

        Assert.assertEquals(repository.count(), 0);

    }

    @Test
    public void shouldCountAllUsers() {

        User user = EntityGenerator.generateUser(0);

        repository.save(user);

        int count = service.countAll();

        Assert.assertEquals(count, 1);

    }

    @Test
    public void shouldFindAllOrderByRegistration() {

        User user = EntityGenerator.generateUser(0);

        repository.save(user);

        List<User> users = service.findAllOrderByRegistration(0, 1);

        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.get(0), user);

    }

}
