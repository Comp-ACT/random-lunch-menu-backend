package com.TeamSk.JMC.Domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    private Long id;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private List<Long> roomId;


    @Builder
    public User(String email, String name, List<Long> roomId)
    {
        this.email = email;
        this.name = name;
        this.roomId = roomId;
    }







}
