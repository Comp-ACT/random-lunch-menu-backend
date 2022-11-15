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
    public boolean saveVoting(@RequestBody VotingMakingDto votingDto) {
        return votingService.saveVoting(votingDto);
    }

    @DeleteMapping("/votings/{restaurantId}/{userId}")
    @ResponseBody
    public boolean deleteVoting(@PathVariable Long restaurantId, @PathVariable Long userId) {
        return votingService.deleteVoting(restaurantId, userId);
    }
}
