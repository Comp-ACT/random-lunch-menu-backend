package com.TeamSk.JMC.Domain.Member;

import com.TeamSk.JMC.Domain.RoomMember.RoomMember;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    private String email;

    private String profileImageURL;

    @OneToMany(mappedBy = "member")
    private List<RoomMember> roomUsers;

    @Builder
    public Member(String name, String email, String profileImageURL) {
        this.name = name;
        this.email = email;
        this.profileImageURL = profileImageURL;
    }
}
