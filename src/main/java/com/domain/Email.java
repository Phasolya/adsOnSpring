package com.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

/**
 * Class {@link Email} with parameters id,mailAddress.
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
@Table(name = "emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "email_id")
    int id;

    @javax.validation.constraints.Email
    @Column(name = "mail_address")
    String mailAddress;

}
