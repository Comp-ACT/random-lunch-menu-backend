package com.TeamSk.JMC.Domain.Room;

import com.TeamSk.JMC.Domain.RecentRestaurant.RecentRestaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue
    @Column(name = "ROOM_ID")
    private Long id;

    private String name;

    private String password;

    private String leaderName;

    @OneToMany(mappedBy = "room")
    private List<RecentRestaurant> recentRestaurants;
}
