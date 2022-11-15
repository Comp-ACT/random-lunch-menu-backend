package com.TeamSk.JMC.Web.Dto.recentRestaurantDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RecentRestaurantMakingDto {
    private final String name;

    private final Long roomId;

    @Builder
    public RecentRestaurantMakingDto(@JsonProperty("restaurantName") String name, @JsonProperty("roomId") Long roomId) {
        this.name = name;
        this.roomId = roomId;
    }
}