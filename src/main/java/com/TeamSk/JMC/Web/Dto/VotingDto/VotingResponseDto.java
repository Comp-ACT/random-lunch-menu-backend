package com.TeamSk.JMC.Web.Dto.VotingDto;


import com.TeamSk.JMC.Domain.Voting.Voting;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VotingResponseDto {

    private final Long id;
    private final Long userId;
    private final boolean agreeFlag;

    @Builder
    public VotingResponseDto(Long id, Long userId, Boolean agreeFlag) {
        this.id = id;
        this.userId = userId;
        this.agreeFlag = agreeFlag;
    }

    public Voting toEntity() {
        return Voting.builder()
                .memberId(userId)
                .agreeFlag(agreeFlag)
                .build();
    }
}
