package com.TeamSk.JMC.Domain.Room;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int password;

    @Column(nullable = false)
    private String name;

    //ToDo Entity에서 List 처리 어떻게 하는지 습득후 수정
//    @Column
//    private User leader;

//    @Column
//    private List<User> member;
//
//    @Column
//    private List<Restaurant> restaurantList;
//
//    @Column
//    private List<Restaurant> recentList;

    @Builder
    public Room(int password, String name)
    {
        this.password = password;
        this.name =name;
    }
}
