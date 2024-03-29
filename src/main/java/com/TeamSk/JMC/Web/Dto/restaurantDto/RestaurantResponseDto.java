package com.TeamSk.JMC.Web.Dto.restaurantDto;

import com.TeamSk.JMC.Domain.Restaurant.Restaurant;
import com.TeamSk.JMC.Web.Dto.VotingDto.VotingResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RestaurantResponseDto {
    private final Long id;
    private final String name;

    private final List<VotingResponseDto> voting;

    @Builder
    public RestaurantResponseDto(Long id, @JsonProperty("name") String name, List<VotingResponseDto> votingList) {
        this.id = id;
        this.name = name;
        this.voting = votingList;
    }

    public Restaurant toEntity() {
        return Restaurant.builder()
                .name(name)
                .build();
    }
}
