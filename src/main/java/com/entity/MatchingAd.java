package com.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "FK_mad_category")
    Category category;
    String title;
    @Column(name = "price_from")
    BigDecimal priceFrom;
    @Column(name = "price_to")
    BigDecimal priceTo;

    @ManyToOne
    @JoinColumn(name = "FK_mad_user")
    User user;

}
