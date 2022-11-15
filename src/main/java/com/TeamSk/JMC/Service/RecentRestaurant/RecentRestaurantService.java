package com.TeamSk.JMC.Service.RecentRestaurant;

import com.TeamSk.JMC.Domain.RecentRestaurant.RecentRestaurant;
import com.TeamSk.JMC.Domain.RecentRestaurant.RecentRestaurantRepository;
import com.TeamSk.JMC.Domain.Room.Room;
import com.TeamSk.JMC.Domain.Room.RoomRepository;
import com.TeamSk.JMC.Service.Rooms.RoomService;
import com.TeamSk.JMC.Web.Dto.recentRestaurantDto.RecentResponseDto;
import com.TeamSk.JMC.Web.Dto.recentRestaurantDto.RecentRestaurantMakingDto;
import com.TeamSk.JMC.Web.Dto.recentRestaurantDto.RecentRestaurantResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RecentRestaurantService {

    private final RoomRepository roomRepository;
    private final RecentRestaurantRepository recentRestaurantRepository;
    private final RoomService roomService;
    private final int maxRecentNum = 5;

    public Long save(RecentRestaurantMakingDto makingDto) {
        Optional<Room> roomOptional = roomRepository.findById(makingDto.getRoomId());
        roomService.roomNotFoundExceptionHandler(makingDto.getRoomId(), roomOptional);

        RecentRestaurant build = RecentRestaurant.builder()
                .room(roomOptional.get())
                .restaurantName(makingDto.getName())
                .build();
        return recentRestaurantRepository.save(build).getId();

    }

    public RecentResponseDto getRecentList(Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        roomService.roomNotFoundExceptionHandler(roomId, roomOptional);

        Room room = roomOptional.get();
        List<RecentRestaurant> recentRestaurants = room.getRecentRestaurants();
        Collections.sort(recentRestaurants);
        List<RecentRestaurantResponseDto> recentList = new ArrayList<>();
        for (int i = 0; i < recentRestaurants.size() && i < maxRecentNum; i++) {
            RecentRestaurantResponseDto build = RecentRestaurantResponseDto.builder()
                    .restaurantName(recentRestaurants.get(i).getRestaurantName())
                    .votingDate(recentRestaurants.get(i).getVotingDate())
                    .build();
            recentList.add(build);
        }
        return RecentResponseDto.builder()
                .recentList(recentList)
                .build();

    }
}
