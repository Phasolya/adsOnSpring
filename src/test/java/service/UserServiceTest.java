package service;

import creator.EntityCreator;
import com.entity.User;
import com.repository.UserRepository;
import com.service.UserService;
import config.ConfigAppTest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(ConfigAppTest.class)
@Sql(scripts = "classpath:scripts/truncate_users_table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    UserRepository repository;

    @Autowired
    UserService service;

    @Test
    public void shouldSaveUser(){

        User user = EntityCreator.getUser(1);

        service.save(user);

        User byId = repository.findById(1).get();

        Assert.assertEquals(user, byId);

    }



}
