package com.TeamSk.JMC.Domain.Voting;

import com.TeamSk.JMC.Domain.Restaurant.Restaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Voting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VOTING_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    private Long memberId;

    private boolean agreeFlag;

    @Builder
    public Voting(Long id, Restaurant restaurant, Long memberId, boolean agreeFlag) {
        this.id = id;
        this.restaurant = restaurant;
        this.memberId = memberId;
        this.agreeFlag = agreeFlag;
    }
}
