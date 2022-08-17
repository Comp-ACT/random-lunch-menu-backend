package com.TeamSk.JMC.Domain.RoomUser;

import com.TeamSk.JMC.Domain.Room.Room;
import com.TeamSk.JMC.Domain.User.Users;
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
    @JoinColumn(name = "USER_ID")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;
}
