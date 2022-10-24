package com.TeamSk.JMC.Service.Voting;

import com.TeamSk.JMC.Domain.Restaurant.Restaurant;
import com.TeamSk.JMC.Domain.Restaurant.RestaurantRepository;
import com.TeamSk.JMC.Domain.Voting.Voting;
import com.TeamSk.JMC.Domain.Voting.VotingRepository;
import com.TeamSk.JMC.Web.Dto.VotingDto.VotingMakingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VotingService {

    private final VotingRepository votingRepository;

    private final RestaurantRepository restaurantRepository;

    public Long save(VotingMakingDto votingDto) {

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(votingDto.getRestaurantId());
        if (restaurantOptional.isPresent()) {
            System.out.println(votingDto.getUserId());
            Voting build = Voting.builder()
                    .restaurant(restaurantOptional.get())
                    .userId(votingDto.getUserId())
                    .agreeFlag(votingDto.isAgreeFlag())
                    .build();
            return votingRepository.save(build).getId();
        }
        //에러 처리
        return votingRepository.save(votingDto.toEntity()).getId();
    }

    public void delete(Long votingId) {
        votingRepository.deleteById(votingId);
    }
}
