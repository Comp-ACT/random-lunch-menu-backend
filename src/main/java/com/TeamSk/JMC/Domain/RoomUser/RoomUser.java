package com.TeamSk.JMC.Domain.RoomUser;

import com.TeamSk.JMC.Domain.Room.Room;
import com.TeamSk.JMC.Domain.Member.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class RoomUser {

    @Id
    @Column(name = "ROOM_USER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;
}
