package com.TeamSk.JMC.Domain.Restaurant;

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

    private String name;
}
