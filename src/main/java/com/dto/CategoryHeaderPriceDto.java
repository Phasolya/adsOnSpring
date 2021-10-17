package com.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * This is class {@link CategoryHeaderPriceDto} is used to convert to
 * {@link com.domain.Category} object.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class CategoryHeaderPriceDto {

    int categoryId;

    @Pattern(regexp = "[\\w\\d\\s]{2,30}")
    String header;

    @Min(0)
    int startRow;
    @Max(20)
    int amount;
    @Pattern(regexp = "name|id")
    String sortParam;
    @Pattern(regexp = "ASC|DESC")
    String sortDirection;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 5, fraction = 2)
    BigDecimal priceFrom;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 5, fraction = 2)
    BigDecimal priceTo;

}
