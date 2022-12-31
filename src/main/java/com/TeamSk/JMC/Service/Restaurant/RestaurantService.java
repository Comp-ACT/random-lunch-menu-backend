package com.TeamSk.JMC.Service.Restaurant;

import com.TeamSk.JMC.Domain.Restaurant.Restaurant;
import com.TeamSk.JMC.Domain.Restaurant.RestaurantRepository;
import com.TeamSk.JMC.Domain.Room.Room;
import com.TeamSk.JMC.Domain.Room.RoomRepository;
import com.TeamSk.JMC.Domain.Voting.Voting;
import com.TeamSk.JMC.Domain.Voting.VotingRepository;
import com.TeamSk.JMC.Exception.handler.Handler;
import com.TeamSk.JMC.Web.Dto.VotingDto.VotingResponseDto;
import com.TeamSk.JMC.Web.Dto.restaurantDto.RestaurantMakingDto;
import com.TeamSk.JMC.Web.Dto.restaurantDto.RestaurantResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RoomRepository roomRepository;

    private final VotingRepository votingRepository;
    private final RestaurantRepository restaurantRepository;
    private final Handler handler;

    public Long save(RestaurantMakingDto restaurantDto) {
        Optional<Room> roomOptional = roomRepository.findById(restaurantDto.getRoomId());
        handler.handleRoomNotFoundException(restaurantDto.getRoomId(), roomOptional);
        System.out.println(restaurantDto.getRoomId());
        Restaurant build = Restaurant.builder()
                .name(restaurantDto.getName())
                .room(roomOptional.get())
                .build();

        return restaurantRepository.save(build).getId();
    }

    public RestaurantResponseDto getRestaurantResponseDto(Long restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        handler.handleRestaurantNotFoundException(restaurantId, restaurantOptional);
        Restaurant restaurant = restaurantOptional.get();
        List<VotingResponseDto> votingList = getVotingList(restaurantId);
        return RestaurantResponseDto.builder()
                .id(restaurantId)
                .name(restaurant.getName())
                .votingList(votingList)
                .build();
    }

    public List<VotingResponseDto> getVotingList(Long restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        handler.handleRestaurantNotFoundException(restaurantId, restaurantOptional);

        Restaurant restaurant = restaurantOptional.get();
        List<Voting> votingList = restaurant.getVoting();
        List<VotingResponseDto> votingDtoList = new ArrayList<>();
        for (int i = 0; i < votingList.size(); i++) {
            Long id = votingList.get(i).getId();
            Long userId = votingList.get(i).getMemberId();
            Boolean agreeFlag = votingList.get(i).isAgreeFlag();
            VotingResponseDto build = VotingResponseDto.builder()
                    .id(id)
                    .userId(userId)
                    .agreeFlag(agreeFlag)
                    .build();
            votingDtoList.add(build);
        }
        return votingDtoList;

    }

    public boolean deleteRestaurant(Long restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        handler.handleRestaurantNotFoundException(restaurantId, restaurantOptional);

        Restaurant restaurant = restaurantOptional.get();
        List<Voting> votingList = restaurant.getVoting();
        for (int i = 0; i < votingList.size(); i++) {
            votingRepository.deleteById(votingList.get(i).getId());
        }
        restaurantRepository.deleteById(restaurantId);
        return true;

    }
}
