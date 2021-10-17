package com.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * This is class {@link CategoryDto} is used to convert to
 * {@link com.domain.Category} object.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class CategoryDto {

    int categoryId;

    @Min(0)
    int startRow;
    @Max(20)
    int amount;
    @Pattern(regexp = "name|id")
    String sortParam;
    @Pattern(regexp = "ASC|DESC")
    String sortDirection;
}
