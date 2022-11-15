package com.TeamSk.JMC.Web.Dto.restaurantDto;

import com.TeamSk.JMC.Domain.Restaurant.Restaurant;
import com.TeamSk.JMC.Web.Dto.VotingDto.VotingResponseDto;
<<<<<<< Updated upstream
=======
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
>>>>>>> Stashed changes
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RestaurantResponseDto {
<<<<<<< Updated upstream
    private final String name;
=======
>>>>>>> Stashed changes

    private final Long id;
    private final String name;


    private final List<VotingResponseDto> voting;

<<<<<<< Updated upstream
    private final List<VotingResponseDto> voting;

    @Builder
    public RestaurantResponseDto(Long id, String name, List<VotingResponseDto> votingList) {
=======
    @Builder
    public RestaurantResponseDto(Long id, @JsonProperty("name") String name, List<VotingResponseDto> votingList) {
        this.id = id;
>>>>>>> Stashed changes
        this.name = name;
        this.voting = votingList;
    }

    public Restaurant toEntity() {
        return Restaurant.builder()
                .name(name)
                .build();
    }
}
