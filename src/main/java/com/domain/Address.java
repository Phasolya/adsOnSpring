package com.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * Class {@link Address} with parameters id,region,city,street,building.
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
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "adress_id")
    int id;

    @Pattern(regexp = "[\\w]{2,15}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(length = 15)
    String region;

    @Pattern(regexp = "[\\w]{2,15}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(length = 15)
    String city;

    @Pattern(regexp = "[\\w\\s0-9]{2,15}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(length = 15)
    String street;

    @Pattern(regexp = "[\\w\\s0-9]{2,15}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(length = 15)
    String building;

}
