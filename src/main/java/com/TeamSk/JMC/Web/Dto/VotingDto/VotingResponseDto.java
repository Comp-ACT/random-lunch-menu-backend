package com.TeamSk.JMC.Web.Dto.VotingDto;

import com.TeamSk.JMC.Domain.Voting.Voting;
import com.TeamSk.JMC.Web.Dto.restaurantDto.RestaurantMakingDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public class VotingResponseDto {

    private RestaurantMakingDto restaurant;

    private Long userId;

    private boolean agreeFlag;

    @Builder
    public VotingResponseDto(@JsonProperty("id")Long id, @JsonProperty("restaurant") RestaurantMakingDto restaurant, @JsonProperty("userId") Long userId, @JsonProperty("agreeFlag")Boolean agreeFlag ) {
        this.restaurant = restaurant;
        this.userId = userId;
        this.agreeFlag = agreeFlag;
    }

    public Voting toEntity()
    {
        return Voting.builder()
                .userId(userId)
                .agreeFlag(agreeFlag)
                .build();
    }
}
