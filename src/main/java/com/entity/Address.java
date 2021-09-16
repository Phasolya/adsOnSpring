package com.entity;

import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @Builder @ToString

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    int id;

    @Pattern(regexp = "[A-Z]{2,15}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(length = 15)
    String region;

    @Pattern(regexp = "[A-Z]{2,15}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(length = 15)
    String city;

    @Pattern(regexp = "[A-Z0-9\\.]{2,15}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(length = 15)
    String street;

    @Pattern(regexp = "[A-Z0-9\\.]{2,15}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(length = 15)
    String building;

}
