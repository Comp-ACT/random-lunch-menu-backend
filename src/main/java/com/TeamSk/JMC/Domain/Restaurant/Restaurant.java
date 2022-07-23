package com.TeamSk.JMC.Domain.Restaurant;

import com.TeamSk.JMC.Domain.User.User;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

public class Restaurant {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private List<User> negativeUserList;

    @Column
    private List<User> positiveUserList;

    @Column
    private double weight;
}
