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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

//    @Column
//    private List<Long> roomId;

    @Builder
    public Users(String email, String name)
    {
        this.email = email;
        this.name = name;
        //this.roomId = roomId;
    }
}
