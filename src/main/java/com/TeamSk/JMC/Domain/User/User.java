package com.TeamSk.JMC.Domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    //ToDo Entity에서 List 처리 어떻게 하는지 습득후 수정
//    @Column
//    private List<Long> roomId;

    @Builder
    public User(String email, String name)
    {
        this.email = email;
        this.name = name;
    }
}
