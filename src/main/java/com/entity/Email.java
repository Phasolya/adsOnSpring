package com.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @Builder @ToString

@Entity
@Table(name = "emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "email_id")
    int id;

    @Pattern(regexp = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(name = "mail_address")
    String mailAddress;

}
