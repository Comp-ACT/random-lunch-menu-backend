package com.TeamSk.JMC.Service.Restaurant;

import com.TeamSk.JMC.Domain.Restaurant.Restaurant;
import com.TeamSk.JMC.Domain.Restaurant.RestaurantRepository;
import com.TeamSk.JMC.Domain.Room.Room;
import com.TeamSk.JMC.Domain.Room.RoomRepository;
import com.TeamSk.JMC.Domain.Voting.Voting;
import com.TeamSk.JMC.Domain.Voting.VotingRepository;
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

    public Long save(RestaurantMakingDto restaurantDto)
    {
        Optional<Room> roomOptional = roomRepository.findById(restaurantDto.getRoomId());
        if (roomOptional.isPresent())
        {
            System.out.println(restaurantDto.getRoomId());
            Restaurant build = Restaurant.builder()
                    .name(restaurantDto.getName())
                    .room(roomOptional.get())
                    .build();
            //RoomResponseDto roomDto = RoomResponseDto.builder().name(room.getName()).password(room.getPassword()).leaderId(room.getLeaderId()).build();
            //restaurantDto.setRoom(room);
            return restaurantRepository.save(build).getId();
        }
        //에러 처리
        return restaurantRepository.save(restaurantDto.toEntity()).getId();
    }

    public RestaurantResponseDto getRestaurantResponseDto(Long restaurantId)
    {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if(restaurantOptional.isPresent())
        {
            System.out.println("=======================");
            Restaurant restaurant = restaurantOptional.get();
            String name = restaurant.getName();
            List<VotingResponseDto> votingList = getVotingList(restaurantId);
            System.out.println("=======================");
            return RestaurantResponseDto.builder()
                    .name(name)
                    .votingList(votingList)
                    .build();
        }
        return null;
    }

    private List<VotingResponseDto> getVotingList(Long restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

        if(restaurantOptional.isPresent())
        {
            Restaurant restaurant = restaurantOptional.get();
            List<Voting> votingList = restaurant.getVoting();
            List<VotingResponseDto> votingDtoList = new ArrayList<>();
            for (int i = 0; i < votingList.size(); i++) {
                Long id = votingList.get(i).getId();
                Long userId = votingList.get(i).getUserId();
                // restaurant이 들어가야 할까요?
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
        return null;
    }

    public void delete(Long restaurantId)
    {
        restaurantRepository.deleteById(restaurantId);
    }


}
