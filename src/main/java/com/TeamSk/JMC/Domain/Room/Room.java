package com.TeamSk.JMC.Domain.Room;

import com.TeamSk.JMC.Domain.Restaurant.Restaurant;
import com.TeamSk.JMC.Domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Room {

    @Id
    private Long id;

    @Column
    private int password;

    @Column
    private String name;

    @Column
    private User leader;

    @Column
    private List<User> member;

    @Column
    private List<Restaurant> restaurantList;

    @Column
    private List<Restaurant> recentList;




}
