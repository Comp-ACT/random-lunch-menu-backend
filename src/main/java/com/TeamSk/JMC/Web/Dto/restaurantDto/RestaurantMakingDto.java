package com.TeamSk.JMC.Web.Dto.restaurantDto;

import com.TeamSk.JMC.Domain.Restaurant.Restaurant;
import com.TeamSk.JMC.Web.Dto.VotingDto.VotingResponseDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RestaurantMakingDto {
    private String name;

    private Long roomId;

    @Builder
    public RestaurantMakingDto(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("roomId") Long roomId) {
        this.name = name;
        this.roomId = roomId;
    }

    public Restaurant toEntity() {
        return Restaurant.builder()
                .name(name)
                .build();
    }
}
