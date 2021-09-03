package com;

import com.config.ConfigApp;
import com.entity.*;
import com.service.AdvertisementService;
import com.service.CategoryService;
import com.service.MailAddressService;
import com.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        System.out.println("Test");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigApp.class);

        UserService userService = context.getBean(UserService.class);

        CategoryService categoryService = context.getBean(CategoryService.class);

        AdvertisementService advertisementService = context.getBean(AdvertisementService.class);

        MailAddressService mailAddressService = context.getBean(MailAddressService.class);

        {
            Address address = Address.builder()
                    .city("Priluki")
                    .building("123/a")
                    .region("Chernigiv")
                    .street("Ptuskin")
                    .build();

        Email email = Email.builder()
                .mailAddress("fg@gmail.com")
                .build();

        Phone phone = Phone.builder()
                .phoneNumber("0509734781")
                .build();

        User user = User.builder()
                .address(address)
                .email(email)
                .phone(phone)
                .role(Role.USER)
                .firstName("Maxim")
                .lastName("Petrovich")
                .login("duck")
                .password("12345")
                .registration(LocalDate.now())
                .build();

//        userService.save(user);

    }

        Category foods = Category.builder()
                .name("foods")
                .build();

        Category guns = Category.builder()
                .name("guns")
                .build();

//        categoryService.save(guns);
//        categoryService.save(foods);

//        System.out.println(categoryService.countAll());
//
//        categoryService.selectAllSortedByName().forEach(System.out::println);
//        categoryService.selectSortedByName(0,1);
//        categoryService.deleteById(53);
//        System.out.println(categoryService.findById(54));

        Advertisement ads = Advertisement.builder()
                .category(categoryService.findById(54))
                .description("brazilian 1-st quality")
                .header("banana")
                .price(BigDecimal.valueOf(24.50))
                .publicationDate(LocalDate.now())
                .user(userService.findById(49))
                .build();

        advertisementService.save(ads);

    }
}
