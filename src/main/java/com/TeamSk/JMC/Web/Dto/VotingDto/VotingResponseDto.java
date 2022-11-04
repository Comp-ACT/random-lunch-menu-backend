package com.TeamSk.JMC.Web.Dto.VotingDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class VotingResponseDto {

    private final Long userId;

    private final boolean agreeFlag;

    @Builder
    public VotingResponseDto(Long id, Long userId, Boolean agreeFlag) {
        this.userId = userId;
        this.agreeFlag = agreeFlag;
    }

}
