package com.TeamSk.JMC.Domain.RecentRestaurant;


import com.TeamSk.JMC.Domain.Room.Room;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class RecentRestaurant {

    @Id
    @GeneratedValue
    @Column(name = "RECENT_RESTAURANT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    private LocalDateTime votingDate;
}
