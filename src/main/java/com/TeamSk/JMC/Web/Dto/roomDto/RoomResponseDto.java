package com.TeamSk.JMC.Web.Dto.roomDto;

import com.TeamSk.JMC.Domain.Room.Room;
import com.TeamSk.JMC.Web.Dto.MemberDto.MemberResponseDto;
import com.TeamSk.JMC.Web.Dto.restaurantDto.RestaurantResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RoomResponseDto {

    private final String name;

    private final String password;

    private final Long LeaderId;

    private final List<MemberResponseDto> memberList;

    private final List<RestaurantResponseDto> restaurantList;

    @Builder
    public RoomResponseDto(Long id, String name, String password, Long leaderId, List<MemberResponseDto> memberList, List<RestaurantResponseDto> restaurantList) {

        this.name = name;
        this.password = password;
        LeaderId = leaderId;
        this.memberList = memberList;
        this.restaurantList = restaurantList;
    }

    public Room toEntity() {
        return Room.builder()
                .name(name)
                .password(password)
                .build();
    }
}
