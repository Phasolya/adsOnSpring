package com.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * Class {@link Category} with parameters id,name.
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
@Builder
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    int id;

    @Pattern(regexp = "[\\w\\s\\d]{2,15}")
    @Column(unique = true, length = 15)
    String name;

}

