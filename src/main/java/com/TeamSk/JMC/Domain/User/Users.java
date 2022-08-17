package com.TeamSk.JMC.Domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue
    @Column(name = "USERS_ID")
    private Long id;

    private String name;

    @Builder
    Users(String name)
    {
        this.name = name;
    }

}
