package service;

import com.domain.User;
import com.dto.CategoryDto;
import com.dto.CategoryHeaderDto;
import com.dto.CategoryHeaderPriceDto;
import com.service.AdvertisementService;
import config.ConfigAppTest;
import com.domain.Advertisement;
import com.repository.AdvertisementRepository;
import org.springframework.data.domain.Page;
import org.springframework.test.context.web.WebAppConfiguration;
import util.EntityGenerator;
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

/**
 * This is a class for testing the class of the
 * {@link AdvertisementService} and its methods.
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
public class AdvertisementServiceTest {

    @Autowired
    AdvertisementRepository repository;

    @Autowired
    AdvertisementService service;

    @Test
    public void shouldSave() {

        Advertisement advertisement = EntityGenerator.generateAds(0);

        Advertisement advertisement1 = EntityGenerator.generateAds(0);

        service.saveAndSentNotifications(advertisement);

        Advertisement byId = repository.findById(advertisement.getId()).get();

        Assert.assertEquals(byId, advertisement);

    }

    @Test
    public void shouldFindById() {

        Advertisement advertisement = EntityGenerator.generateAds(0);

        repository.save(advertisement);

        Advertisement byId = service.find(advertisement.getId());

        Assert.assertEquals(byId, advertisement);

    }

    @Test
    public void shouldUpdate() {

        Advertisement advertisement = EntityGenerator.generateAds(0);

        advertisement.setHeader("updated");

        service.updateAndSentNotifications(advertisement);

        Advertisement byId = repository.findById(advertisement.getId()).get();

        Assert.assertEquals(byId, advertisement);

    }

    @Test
    public void shouldDeleteById() {

        Advertisement advertisement = EntityGenerator.generateAds(0);

        repository.save(advertisement);

        service.delete(advertisement.getId());

        Assert.assertEquals(repository.count(), 0);

    }

    @Test
    public void shouldCountAll() {

        Advertisement advertisement = EntityGenerator.generateAds(0);

        repository.save(advertisement);

        int count = service.countAll();

        Assert.assertEquals(count, 1);

    }

    @Test
    public void shouldDeleteAllNotActive() {

        Advertisement advertisement = EntityGenerator.generateAds(0);

        advertisement.setActive(false);

        repository.save(advertisement);

        service.deleteAllNotActive();

        Assert.assertEquals(repository.count(), 0);

    }

    @Test
    public void shouldDeleteByUser() {

        Advertisement advertisement = EntityGenerator.generateAds(0);

        repository.save(advertisement);

        User user = advertisement.getUser();

        service.deleteAllByUser(user);

        Assert.assertEquals(repository.count(), 0);

    }

    @Test
    public void shouldGetByCategoryDto() {

        Advertisement ad = EntityGenerator.generateAds(0);

        repository.save(ad);

        CategoryDto categoryDto = EntityGenerator.generateCategoryDto(ad.getCategory().getId());

        Page<Advertisement> ads = service.getByCategoryDto(categoryDto);

        Assert.assertEquals(ads.getTotalElements(), 1);

        Assert.assertEquals(ads.toList().get(0), ad);

    }

    @Test
    public void shouldGetByCategoryHeaderDto() {

        Advertisement ad = EntityGenerator.generateAds(0);

        repository.save(ad);

        CategoryHeaderDto categoryHeaderDto = EntityGenerator.generateCategoryHeaderDto(ad.getCategory().getId());

        Page<Advertisement> ads = service.getByCategoryHeaderDto(categoryHeaderDto);

        Assert.assertEquals(ads.getTotalElements(), 1);

        Assert.assertEquals(ads.toList().get(0), ad);

    }

    @Test
    public void shouldGetByCategoryHeaderPriceDto() {

        Advertisement ad = EntityGenerator.generateAds(0);

        repository.save(ad);

        CategoryHeaderPriceDto chpdto = EntityGenerator.generateCategoryHeaderPriceDto(ad.getCategory().getId());

        Page<Advertisement> ads = service.getByCategoryHeaderPriceDto(chpdto);

        Assert.assertEquals(ads.getTotalElements(), 1);

        Assert.assertEquals(ads.toList().get(0), ad);

    }

}
