package com.TeamSk.JMC.Web;

import com.TeamSk.JMC.Service.Voting.VotingService;
import com.TeamSk.JMC.Web.Dto.VotingDto.VotingMakingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class VotingController {
    private final VotingService votingService;

    @PostMapping("/votings")
    @ResponseBody
    public Long save(@RequestBody VotingMakingDto votingDto) {
        return votingService.save(votingDto);
    }

    @DeleteMapping("/votings/{restaurantId}/{userId}")
    @ResponseBody
    public void deleteVoting(@PathVariable Long votingId) {
        votingService.delete(votingId);
    }
}
