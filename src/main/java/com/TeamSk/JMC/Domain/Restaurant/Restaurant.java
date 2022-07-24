package com.TeamSk.JMC.Domain.Restaurant;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

//    @Column
//    private List<User> negativeUserList;
//
//    @Column
//    private List<User> positiveUserList;

    @Builder
    public Restaurant(String name)
    {
        this.name = name;

    }
}
