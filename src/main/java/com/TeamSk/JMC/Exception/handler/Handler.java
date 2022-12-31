package com.TeamSk.JMC.Exception.handler;

import com.TeamSk.JMC.Domain.Member.Member;
import com.TeamSk.JMC.Domain.Restaurant.Restaurant;
import com.TeamSk.JMC.Domain.Room.Room;
import com.TeamSk.JMC.Exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class Handler {
    public void handleMemberNotFoundException(Long memberId, Optional<Member> memberOptional) {
        if (!memberOptional.isPresent()) {
            log.error("MemberNotFoundException : memberID(" + memberId + ")에 대한 member가 없습니다.");
            throw new NotFoundException("MemberNotFoundException : memberID(" + memberId + ")에 대한 member가 없습니다.");
        }
    }

    public void handleRoomNotFoundException(Long roomId, Optional<Room> roomOptional) {
        if (!roomOptional.isPresent()) {
            log.error("RoomNotFoundException : roomID(" + roomId + ")에 대한 room이 없습니다.");
            throw new NotFoundException("RoomNotFoundException : roomID(" + roomId + ")에 대한 room이 없습니다.");
        }
    }

    public void handleRestaurantNotFoundException(Long restaurantId, Optional<Restaurant> restaurantOptional) {
        if (!restaurantOptional.isPresent()) {
            log.error("RoomNotFoundException : restaurantId(" + restaurantId + ")에 대한 restaurant 없습니다.");
            throw new NotFoundException("RestaurantNotFoundException : restaurantId(" + restaurantId + ")에 대한 restaurant 없습니다.");
        }
    }
}
