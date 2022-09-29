package com.TeamSk.JMC.Domain.RecentRestaurant;


import com.TeamSk.JMC.Domain.Room.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class RecentRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RECENT_RESTAURANT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    private LocalDateTime votingDate;
}
