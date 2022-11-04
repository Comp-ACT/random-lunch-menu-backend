package com.TeamSk.JMC.Domain.RecentRestaurant;


import com.TeamSk.JMC.Domain.Room.Room;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@Entity
public class RecentRestaurant implements Comparable<RecentRestaurant> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECENT_RESTAURANT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    private String restaurantName;

    private String votingDate;

    @Builder
    public RecentRestaurant(Room room, String restaurantName) {
        this.room = room;
        this.restaurantName = restaurantName;
        this.votingDate =
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public int compareTo(RecentRestaurant r) {
        LocalDateTime thisDateTime = LocalDateTime.parse(this.votingDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime rDateTime = LocalDateTime.parse(r.getVotingDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        if (thisDateTime.isAfter(rDateTime))
            return -1;
        else if (thisDateTime.isBefore(rDateTime))
            return 1;
        else
            return 0;
    }
}
