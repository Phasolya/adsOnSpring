package com.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ads_id")
    int id;

    @Valid
    @ManyToOne
    @JoinColumn(name = "FK_ads_category")
    Category category;

    @Valid
    @ManyToOne
    @JoinColumn(name = "FK_ads_user")
    User user;

    @Pattern(regexp = ".{2,30}")
    @Column(length = 30)
    String header;

    // TODO: 11.09.2021 VALIDATE IT
    @Column(name = "publication_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate publicationDate;

    @NotNull
    @Column(name = "is_active")
    Boolean isActive;

    @Pattern(regexp = ".{2,300}")
    @Column(length = 300)
    String description;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 5, fraction = 2)
    BigDecimal price;

}
