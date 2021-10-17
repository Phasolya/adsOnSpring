package service;


import com.domain.MatchingAd;
import com.repository.MatchingAdRepository;
import com.service.MatchingAdService;
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
 * {@link MatchingAdService} and its methods.
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
public class MatchingAdServiceTest {

    @Autowired
    MatchingAdRepository repository;

    @Autowired
    MatchingAdService service;

    @Test
    public void shouldSaveMatchingAd() {

        MatchingAd matchingAd = EntityGenerator.generateMatchingAd(0);

        service.save(matchingAd);

        MatchingAd byId = repository.findById(matchingAd.getId()).get();

        Assert.assertEquals(byId, matchingAd );

    }

    @Test
    public void shouldFindMatchingAdById() {

        MatchingAd matchingAd = EntityGenerator.generateMatchingAd(0);

        repository.save(matchingAd);

        MatchingAd byId = service.find(matchingAd.getId());

        Assert.assertEquals(byId, matchingAd);

    }

    @Test
    public void shouldUpdateMatchingAd() {

        MatchingAd matchingAd = EntityGenerator.generateMatchingAd(0);

        matchingAd.setTitle("updated");

        service.update(matchingAd);

        MatchingAd byId = repository.findById(matchingAd.getId()).get();

        Assert.assertEquals(byId, matchingAd);

    }

    @Test
    public void shouldDeleteMatchingAdById() {

        MatchingAd matchingAd = EntityGenerator.generateMatchingAd(0);

        repository.save(matchingAd);

        service.delete(matchingAd.getId());

        Assert.assertEquals(0, repository.count());

    }

    @Test
    public void shouldGetByUserOrderById() {

        MatchingAd matchingAd = EntityGenerator.generateMatchingAd(0);

        repository.save(matchingAd);

        List<MatchingAd> mads = service.getByUserOrderById(matchingAd.getUser().getId());

        Assert.assertEquals(1, mads.size());
        Assert.assertEquals(mads.get(0), matchingAd );

    }
}
