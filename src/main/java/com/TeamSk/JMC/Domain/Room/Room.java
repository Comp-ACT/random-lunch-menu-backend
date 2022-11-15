package com.TeamSk.JMC.Domain.Room;

import com.TeamSk.JMC.Domain.RecentRestaurant.RecentRestaurant;
import com.TeamSk.JMC.Domain.Restaurant.Restaurant;
import com.TeamSk.JMC.Domain.RoomMember.RoomMember;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Long id;

    private String name;

    private String password;

    private Long leaderId;

    @OneToMany(mappedBy = "room")
    private List<RecentRestaurant> recentRestaurants;

    @OneToMany(mappedBy = "room")
    private List<RoomMember> roomMembers;

    @OneToMany(mappedBy = "room")
    private List<Restaurant> restaurants;

    @Builder
    public Room(Long id, String name, String password, Long leaderId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.leaderId = leaderId;
    }
}
