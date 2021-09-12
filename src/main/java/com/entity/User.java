package com.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Builder
@ToString

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    int id;

    @Pattern(regexp = "[A-Za-z0-9]{6,10}")
    @Column(nullable = false, unique = true, length = 10)
    String login;

    @Pattern(regexp = ".{6,10}")
    @Column(nullable = false, length = 10)
    String password;

    @Pattern(regexp = "[A-Z][a-z]{1,14}")
    @Column(name = "first_name", length = 15)
    String firstName;

    @Pattern(regexp = "[A-Z][a-z]{1,14}")
    @Column(name = "last_name", length = 15)
    String lastName;

    @Valid
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "FK_user_mail")
    Email email;

    @Valid
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "FK_user_phone")
    Phone phone;

    @Valid
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "FK_user_address")
    Address address;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    Role role;

    // TODO: 11.09.2021 validation for LocalDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate registration;

}
