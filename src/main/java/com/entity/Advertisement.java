package com.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    @PastOrPresent
    // TODO: 11.09.2021 VALIDATE IT
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "publication_date")
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
