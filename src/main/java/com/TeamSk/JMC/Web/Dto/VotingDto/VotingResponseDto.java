package com.TeamSk.JMC.Web.Dto.VotingDto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class VotingResponseDto {

    private final Long userId;

    private final boolean agreeFlag;

    @Builder
    public VotingResponseDto(@JsonProperty("id") Long id, @JsonProperty("userId") Long userId, @JsonProperty("agreeFlag") Boolean agreeFlag) {
        this.userId = userId;
        this.agreeFlag = agreeFlag;
    }

}
