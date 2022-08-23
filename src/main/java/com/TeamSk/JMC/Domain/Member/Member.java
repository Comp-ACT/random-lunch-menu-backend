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
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "member")
    private List<RoomUser> roomUsers;

    @Builder
    Member(String name, String email)
    {
        this.name = name;
        this.email = email;
    }
}
