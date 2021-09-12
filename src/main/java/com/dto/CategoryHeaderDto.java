package com.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class CategoryHeaderDto {
    int categoryId;

    @Pattern(regexp = ".{2,30}")
    String header;

    @Min(0)
    int startRow;
    @Max(20)
    int amount;
    @Pattern(regexp = "name|id")
    String sortParam;
    @Pattern(regexp = "ASC|DESC")
    String sortDirection;
}
