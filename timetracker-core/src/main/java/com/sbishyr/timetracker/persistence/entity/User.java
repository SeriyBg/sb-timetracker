package com.sbishyr.timetracker.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "sbtt_user")
@EqualsAndHashCode(exclude = "id")
@ToString
public class User {

    private User() {
    }

    public User(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter @Setter(AccessLevel.PRIVATE)
    private String username;

    @Getter @Setter(AccessLevel.PRIVATE)
    private String firstName;

    @Getter @Setter(AccessLevel.PRIVATE)
    private String lastName;
}
