package com.TeamSk.JMC.Domain.User;

import com.TeamSk.JMC.Domain.RoomUser.RoomUser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue
    @Column(name = "USERS_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<RoomUser> roomUsers;

    @Builder
    Users(String name)
    {
        this.name = name;
    }
}
