package com.TeamSk.JMC.Domain.RoomMember;

import com.TeamSk.JMC.Domain.Room.Room;
import com.TeamSk.JMC.Domain.Member.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class RoomMember {

    @Id
    @GeneratedValue
    @Column(name = "ROOM_MEMBER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @Builder
    public RoomMember(Member member, Room room) {
        this.member = member;
        this.room = room;
    }
}
