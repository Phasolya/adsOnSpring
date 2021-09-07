package com;

import com.config.ConfigApp;
import com.entity.*;
import com.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Test {

    private static final String GREEN_COLOR = "\033[0;32m";
    public static final String RESET = "\033[0m";

    public static void main(String[] args) {

        System.out.println(GREEN_COLOR + "TEST START" + RESET);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigApp.class);

        UserService userService = context.getBean(UserService.class);

        CategoryService categoryService = context.getBean(CategoryService.class);

        AdvertisementService advertisementService = context.getBean(AdvertisementService.class);

        MailAddressService mailAddressService = context.getBean(MailAddressService.class);

        MatchingAdService matchingAdService = context.getBean(MatchingAdService.class);

        System.out.println(GREEN_COLOR + "GET ALL BEANS : DONE!" + RESET);

        User user0 = createUser(0);
        User user1 = createUser(1);
        User user2 = createUser(2);
        User user3 = createUser(3);
        User user4 = createUser(4);

        userService.save(user0);
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        System.out.println(userService.countAll());
        userService.deleteById(user0.getId());
        System.out.println(userService.findById(user1.getId()));
        user4.setFirstName("UPDATED");
        userService.update(user4);
        userService.findAllOrderByRegistration(0, 3).forEach(System.out::println);

        System.out.println(GREEN_COLOR + "USER SERVICE TESTS : DONE!" + RESET);

        Category category0 = Category.builder().name("category0").build();
        Category category1 = Category.builder().name("category1").build();
        Category category2 = Category.builder().name("category2").build();
        Category category3 = Category.builder().name("category3").build();

        categoryService.save(category0);
        categoryService.save(category1);
        categoryService.save(category2);
        categoryService.save(category3);
        categoryService.deleteById(category0.getId());
        System.out.println(categoryService.findById(category1.getId()));
        System.out.println(categoryService.countAll());
        category3.setName("UPDATED3");
        categoryService.update(category3);
        categoryService.getSortedByName(0, 2).forEach(System.out::println);

        System.out.println(GREEN_COLOR + "CATEGORY SERVICE TESTS : DONE!" + RESET);

        Advertisement ads0 = createAds(0, category1, user1);
        Advertisement ads1 = createAds(1, category1, user1);
        Advertisement ads2 = createAds(2, category2, user2);
        Advertisement ads3 = createAds(3, category3, user3);
        Advertisement ads4 = createAds(4, category3, user3);

        advertisementService.saveAndSentNotifications(ads0);
        advertisementService.saveAndSentNotifications(ads1);
        advertisementService.saveAndSentNotifications(ads2);
        advertisementService.saveAndSentNotifications(ads3);
        advertisementService.saveAndSentNotifications(ads4);
        advertisementService.deleteById(ads0.getId());
        ads1.setHeader("UPDATED");
        advertisementService.updateAndSentNotifications(ads1);
        System.out.println(advertisementService.countAll());
        System.out.println(advertisementService.findById(ads2.getId()));
        System.out.println(advertisementService.countByCategory(category3));
        System.out.println(advertisementService.countByCategoryAndHeader(category1, "title#"));
        System.out.println(advertisementService.countByCategoryAndHeaderAndPriceBetween(
                category2, "title", BigDecimal.valueOf(0), BigDecimal.valueOf(100)));
        advertisementService.getByCategoryAndHeaderAndPriceBetweenOrderByPrice(
                category3, "title", BigDecimal.valueOf(0), BigDecimal.valueOf(100), 0, 2)
                .forEach(System.out::println);

        System.out.println(GREEN_COLOR + "ADVERTISEMENT SERVICE TESTS : DONE!" + RESET);

        MatchingAd m1 = MatchingAd.builder()
                .category(category1)
                .title("title")
                .user(user1)
                .priceFrom(BigDecimal.valueOf(0))
                .priceTo(BigDecimal.valueOf(1000))
                .build();
        MatchingAd m2 = MatchingAd.builder()
                .category(category3)
                .title("tit")
                .user(user3)
                .priceFrom(BigDecimal.valueOf(0))
                .priceTo(BigDecimal.valueOf(1000))
                .build();

        matchingAdService.save(m1);
        matchingAdService.save(m2);
        matchingAdService.deleteById(m1.getId());
        System.out.println(matchingAdService.getByUserOrderById(user3));
        m2.setTitle("title");
        matchingAdService.update(m2);

        System.out.println(GREEN_COLOR + "MATCHING AD SERVICE TESTS : DONE!" + RESET);

        mailAddressService.sendEmails(ads3);

        System.out.println(GREEN_COLOR + "MAIL ADDRESS SERVICE TESTS : DONE!" + RESET);

    }

    public static Advertisement createAds(int uniqueParam, Category category, User user) {

        BigDecimal price = new BigDecimal(uniqueParam * 3 / 2);
        price = price.setScale(2, RoundingMode.CEILING);

        return Advertisement.builder()
                .category(category)
                .description("descr#" + uniqueParam)
                .header("title#" + uniqueParam)
                .price(price)
                .publicationDate(LocalDate.now())
                .user(user)
                .build();
    }

    public static User createUser(int uniqueParam) {

        Address address = Address.builder()
                .city("city#" + uniqueParam)
                .building("build#" + uniqueParam)
                .region("reg#" + uniqueParam)
                .street("st#" + uniqueParam)
                .build();

        Email email = Email.builder()
                .mailAddress(uniqueParam + "@gmail.com")
                .build();

        Phone phone = Phone.builder()
                .phoneNumber("050" + "555" + "00" + uniqueParam)
                .build();

        return User.builder()
                .address(address)
                .email(email)
                .phone(phone)
                .role(Role.USER)
                .firstName("firstName" + uniqueParam)
                .lastName("lastName" + uniqueParam)
                .login("login" + uniqueParam)
                .password("pass" + uniqueParam)
                .registration(LocalDate.now())
                .build();
    }
}
