package com.TeamSk.JMC.Web.Dto.restaurantDto;

import com.TeamSk.JMC.Domain.Restaurant.Restaurant;
import com.TeamSk.JMC.Web.Dto.VotingDto.VotingResponseDto;
import com.TeamSk.JMC.Web.Dto.roomDto.RoomResponseDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RestaurantResponseDto {
    private String name;


    private List<VotingResponseDto> voting;
    @Builder
    public RestaurantResponseDto(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("votingList") List<VotingResponseDto> votingList) {
        this.name = name;
        this.voting = votingList;
    }

    public Restaurant toEntity() {
        return Restaurant.builder()
                .name(name)
                .build();
    }
}
