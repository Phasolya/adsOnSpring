package com.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString @Builder

@Entity
@Table(name = "matching_ads")
public class MatchingAd {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mad_id")
    int id;

    @Valid
    @ManyToOne
    @JoinColumn(name = "FK_mad_category")
    Category category;

    @Pattern(regexp = "[A-Z][a-z]{1,14}")
    @Column(length = 15)
    String title;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 5, fraction = 2)
    @Column(name = "price_from")
    BigDecimal priceFrom;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 5, fraction = 2)
    @Column(name = "price_to")
    BigDecimal priceTo;

    @Valid
    @ManyToOne
    @JoinColumn(name = "FK_mad_user")
    User user;

}
