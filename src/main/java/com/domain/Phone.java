package com.domain;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * Class {@link Phone} with parameters id,phoneNumber.
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
@ToString
@Builder
@EqualsAndHashCode

@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "phone_id")
    int id;

    @Pattern(regexp = "\\+[0-9]{12}")
    @Column(name = "phone_number")
    String phoneNumber;

}
