package com.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Class {@link Advertisement} with parameters id,header,publicationDate,isActive,description,price,
 * {@link Category}, {@link User}.
 * Implements pattern "builder", equals and hashCode methods.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ads_id")
    int id;

    @Valid
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_ads_category")
    Category category;

    @Valid
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_ads_user")
    User user;

    @Pattern(regexp = "[\\w\\s0-9]{2,30}")
    @Column(length = 30)
    String header;

    @PastOrPresent
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "publication_date")
    LocalDate publicationDate;

    @NotNull
    @Column(name = "is_active")
    boolean isActive;

    @Pattern(regexp = ".{2,300}")
    @Column(length = 300)
    String description;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 5, fraction = 2)
    BigDecimal price;

}
