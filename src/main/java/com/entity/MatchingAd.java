package com.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "matching_ads")
public class MatchingAd {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "matching_ad__id")
    int id;

    @ManyToOne
    @JoinColumn(name = "FK__matching_ads__category")
    Category category;
    String title;
    BigDecimal priceFrom;
    BigDecimal priceTo;
    @ManyToOne
    @JoinColumn(name = "FK__matching_ads__user")
    User user;

}
