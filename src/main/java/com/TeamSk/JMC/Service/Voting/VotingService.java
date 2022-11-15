package com.TeamSk.JMC.Service.Voting;

import com.TeamSk.JMC.Domain.Member.Member;
import com.TeamSk.JMC.Domain.Member.MemberRepository;
import com.TeamSk.JMC.Domain.Restaurant.Restaurant;
import com.TeamSk.JMC.Domain.Restaurant.RestaurantRepository;
import com.TeamSk.JMC.Domain.Voting.Voting;
import com.TeamSk.JMC.Domain.Voting.VotingRepository;
import com.TeamSk.JMC.Web.Dto.VotingDto.VotingMakingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VotingService {
    private final VotingRepository votingRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;

    public boolean saveVoting(VotingMakingDto votingDto) {

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(votingDto.getRestaurantId());
        Optional<Member> memberOptional = memberRepository.findById(votingDto.getUserId());

        if (restaurantOptional.isPresent() && memberOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            List<Voting> votingList = restaurant.getVoting();
            for (int i = 0; i < votingList.size(); i++) {
                if (votingDto.getUserId().equals(votingList.get(i).getUserId())) {
                    votingRepository.deleteById(votingList.get(i).getId());
                }
            }

            System.out.println(votingDto.getUserId());
            Voting build = Voting.builder()
                    .restaurant(restaurantOptional.get())
                    .userId(votingDto.getUserId())
                    .agreeFlag(votingDto.isAgreeFlag())
                    .build();
            votingRepository.save(build).getId();
            return true;
        }
        //에러 처리
        return false;
    }

    public boolean deleteVoting(Long restaurantId, Long userId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            List<Voting> votingList = restaurant.getVoting();
            for (int i = 0; i < votingList.size(); i++) {
                Long votingId = votingList.get(i).getId();
                Long listUserId = votingList.get(i).getUserId();
                System.out.println(votingList.get(i).getUserId());
                System.out.println(userId);
                if (userId.equals(listUserId)) {
                    System.out.println("삭제중입니다.");
                    votingRepository.deleteById(votingId);
                    return true;
                }
            }
        }
        return false;
    }
}
