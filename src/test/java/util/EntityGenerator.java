package util;

import com.domain.*;
import com.dto.CategoryDto;
import com.dto.CategoryHeaderDto;
import com.dto.CategoryHeaderPriceDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class EntityGenerator {

    public static Address generateAddress(int id) {

        return Address.builder()
                .id(id)
                .region("Test")
                .city("Test")
                .street("Tests " + id)
                .building("Building " + id)
                .build();

    }

    public static Advertisement generateAds(int id) {
        return Advertisement.builder()
                .id(id)
                .category(generateCategory(id))
                .user(generateUser(id))
                .header("header")
                .publicationDate(LocalDate.now())
                .isActive(true)
                .description("description")
                .price(BigDecimal.valueOf(1.00).setScale(2, RoundingMode.FLOOR))
                .build();
    }

    public static Category generateCategory(int id) {

        return Category.builder()
                .id(id)
                .name("test")
                .build();
    }

    public static Email generateEmail(int id) {

        return Email.builder()
                .id(id)
                .mailAddress("test@mail.com")
                .build();
    }

    public static MatchingAd generateMatchingAd(int id) {

        return MatchingAd.builder()
                .id(id)
                .title("title")
                .priceFrom(BigDecimal.valueOf(1.00))
                .priceTo(BigDecimal.valueOf(10.00))
                .user(generateUser(id))
                .category(generateCategory(id))
                .build();
    }

    public static Phone generatePhone(int id) {

        return Phone.builder()
                .id(id)
                .phoneNumber("+380673876781")
                .build();

    }

    public static Role generateRole(int id) {

        return Role.builder()
                .name("USER")
                .build();

    }

    public static User generateUser(int id) {

        Set<Role> roles = new HashSet<>();
        roles.add(generateRole(0));

        return User.builder()
                .id(id)
                .login("login" + id)
                .password("password" + id)
                .registration(LocalDate.now())
                .firstName("First")
                .lastName("Last")
                .roles(roles)
                .phone(generatePhone(0))
                .email(generateEmail(0))
                .address(generateAddress(0))
                .build();

    }

    public static CategoryDto generateCategoryDto(int categoryId) {

        return CategoryDto.builder()
                .startRow(0)
                .amount(10)
                .sortParam("header")
                .sortDirection("ASC")
                .categoryId(categoryId)
                .build();
    }

    public static CategoryHeaderDto generateCategoryHeaderDto(int categoryId) {

        return CategoryHeaderDto.builder()
                .startRow(0)
                .amount(10)
                .sortParam("header")
                .sortDirection("ASC")
                .categoryId(categoryId)
                .header("header")
                .build();
    }

    public static CategoryHeaderPriceDto generateCategoryHeaderPriceDto(int categoryId) {

        return CategoryHeaderPriceDto.builder()
                .startRow(0)
                .amount(10)
                .priceFrom(BigDecimal.ONE)
                .priceTo(BigDecimal.TEN)
                .sortParam("header")
                .sortDirection("ASC")
                .categoryId(categoryId)
                .header("header")
                .build();
    }

}
