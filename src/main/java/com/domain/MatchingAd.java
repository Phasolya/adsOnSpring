package com.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Class {@link MatchingAd} with parameters id,title,priceFrom,priceTo,
 * {@link Category}, {@link User}.
 * Implements pattern "builder", equals and hashCode methods.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */

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
    @Column(name = "mad_id")
    int id;

    @Valid
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_mad_category")
    Category category;

    @Pattern(regexp = "[\\w\\s0-9]{2,30}")
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
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_mad_user")
    User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchingAd that = (MatchingAd) o;
        return id == that.id &&
                Objects.equals(category, that.category) &&
                Objects.equals(title, that.title) &&
                Objects.equals(priceFrom.setScale(2, RoundingMode.FLOOR),
                        that.priceFrom.setScale(2, RoundingMode.FLOOR)) &&
                Objects.equals(priceTo.setScale(2, RoundingMode.FLOOR),
                        that.priceTo.setScale(2, RoundingMode.FLOOR)) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, title, priceFrom, priceTo, user);
    }
}
