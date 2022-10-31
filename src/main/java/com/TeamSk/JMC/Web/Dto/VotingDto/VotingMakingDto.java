package com.TeamSk.JMC.Web.Dto.VotingDto;

import com.TeamSk.JMC.Domain.Voting.Voting;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class VotingMakingDto {

    private final Long restaurantId;

    private final Long userId;

    private final boolean agreeFlag;

    @Builder
    public VotingMakingDto(@JsonProperty("id") Long id, @JsonProperty("restaurantId") Long restaurantId, @JsonProperty("userId") Long userId, @JsonProperty("agreeFlag") Boolean agreeFlag) {
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.agreeFlag = agreeFlag;
    }

    public Voting toEntity() {
        return Voting.builder()
                .agreeFlag(agreeFlag)
                .build();
    }
}
