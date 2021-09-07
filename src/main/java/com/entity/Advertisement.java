package com.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @Builder @ToString

@Entity
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ads_id")
    int id;

    @ManyToOne
    @JoinColumn(name = "FK_ads_category")
    Category category;

    @ManyToOne
    @JoinColumn(name = "FK_ads_user")
    User user;

    @Column(length = 30)
    String header;

    @Column(name = "publication_date")
    LocalDate publicationDate;

    @Column(length = 300)
    String description;

    BigDecimal price;

}
