package com.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * Class {@link Role} with parameters id,name.
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
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    int id;

    @Pattern(regexp = "USER|ADMIN")
    @Column(nullable = false, unique = true, length = 5)
    String name;
}
