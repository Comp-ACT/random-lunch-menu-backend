package com.TeamSk.JMC.Service.Voting;

import com.TeamSk.JMC.Domain.Member.Member;
import com.TeamSk.JMC.Domain.Member.MemberRepository;
import com.TeamSk.JMC.Domain.Restaurant.Restaurant;
import com.TeamSk.JMC.Domain.Restaurant.RestaurantRepository;
import com.TeamSk.JMC.Domain.Voting.Voting;
import com.TeamSk.JMC.Domain.Voting.VotingRepository;
import com.TeamSk.JMC.Exception.MemberNotFoundException;
import com.TeamSk.JMC.Exception.handler.Handler;
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
    private final Handler handler;

    public boolean saveVoting(VotingMakingDto votingDto) {
        Long restaurantId = votingDto.getRestaurantId();
        Long memberId = votingDto.getMemberId();
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        handler.restaurantNotFoundExceptionHandler(restaurantId, restaurantOptional);
        handler.memberNotFoundExceptionHandler(memberId, memberOptional);

        Restaurant restaurant = restaurantOptional.get();
        deleteExistedVoting(votingDto, restaurant);
        Voting build = Voting.builder()
                .restaurant(restaurantOptional.get())
                .memberId(votingDto.getMemberId())
                .agreeFlag(votingDto.isAgreeFlag())
                .build();
        votingRepository.save(build).getId();
        return true;
    }

    private void deleteExistedVoting(VotingMakingDto votingDto, Restaurant restaurant) {
        List<Voting> votingList = restaurant.getVoting();
        for (int i = 0; i < votingList.size(); i++) {
            if (votingDto.getMemberId().equals(votingList.get(i).getMemberId())) {
                votingRepository.deleteById(votingList.get(i).getId());
            }
        }
    }

    public boolean deleteVoting(Long restaurantId, Long userId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        handler.restaurantNotFoundExceptionHandler(restaurantId, restaurantOptional);
        Restaurant restaurant = restaurantOptional.get();
        List<Voting> votingList = restaurant.getVoting();
        boolean flag = false;
        Long votingId = 0L;
        Long listUserId = 0L;
        for (int i = 0; i < votingList.size(); i++) {
            votingId = votingList.get(i).getId();
            listUserId = votingList.get(i).getMemberId();

            if (userId.equals(listUserId)) {
                flag = true;
                break;
            }
        }
        if (!flag)
            throw new MemberNotFoundException("votingList에 해당 member(" + listUserId + ")가 없습니다.");
        votingRepository.deleteById(votingId);
        return true;
    }


}
