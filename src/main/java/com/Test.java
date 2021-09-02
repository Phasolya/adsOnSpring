package com;

import com.config.ConfigApp;
import com.entity.*;
import com.service.AdvertisementService;
import com.service.CategoryService;
import com.service.MailAddressService;
import com.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        System.out.println("Test");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigApp.class);

        UserService userService = context.getBean(UserService.class);

        CategoryService categoryService = context.getBean(CategoryService.class);

        AdvertisementService advertisementService = context.getBean(AdvertisementService.class);

        MailAddressService mailAddressService = context.getBean(MailAddressService.class);

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

        userService.save(user);

        System.out.println("IN PROGRAM : " + user.getId());

        user.setFirstName("changed");

        userService.update(user);

    }
}
