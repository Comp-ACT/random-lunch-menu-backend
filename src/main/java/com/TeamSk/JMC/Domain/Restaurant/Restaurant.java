package com.TeamSk.JMC.Domain.Restaurant;

import com.TeamSk.JMC.Domain.Room.Room;
import com.TeamSk.JMC.Domain.Voting.Voting;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    @Column(name = "RESTAURANT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @OneToMany(mappedBy = "restaurant")
    private List<Voting> voting;

    private String name;

    @Builder
    public Restaurant(Long id, Room room, String name) {
        this.id = id;
        this.room = room;
        this.name = name;
    }
}
