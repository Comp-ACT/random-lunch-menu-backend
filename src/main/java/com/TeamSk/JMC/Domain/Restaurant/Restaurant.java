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

    //ToDo Entity에서 List 처리 어떻게 하는지 습득후 수정
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
