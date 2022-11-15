package com.TeamSk.JMC.Web.Dto.VotingDto;

<<<<<<< Updated upstream
=======
import com.TeamSk.JMC.Domain.Voting.Voting;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
>>>>>>> Stashed changes
import lombok.Builder;
import lombok.Getter;

@Getter
public class VotingResponseDto {

    private final Long id;
    private final Long userId;

    private final boolean agreeFlag;

    @Builder
<<<<<<< Updated upstream
    public VotingResponseDto(Long id, Long userId, Boolean agreeFlag) {
=======
    public VotingResponseDto(Long id, Long userId, Long restaurantId, Boolean agreeFlag) {
        this.id = id;
>>>>>>> Stashed changes
        this.userId = userId;
        this.agreeFlag = agreeFlag;
    }

    public Voting toEntity() {
        return Voting.builder()
                .userId(userId)
                .agreeFlag(agreeFlag)
                .build();
    }
}
