package com.TeamSk.JMC.Domain.Restaurant;

import com.TeamSk.JMC.Domain.Room.Room;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private String name;
}
