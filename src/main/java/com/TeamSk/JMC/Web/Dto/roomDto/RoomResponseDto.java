package com.TeamSk.JMC.Web.Dto.roomDto;

import com.TeamSk.JMC.Domain.Room.Room;
import com.TeamSk.JMC.Web.Dto.MemberDto.MemberResponseDto;
import com.TeamSk.JMC.Web.Dto.restaurantDto.RestaurantResponseDto;
import com.TeamSk.JMC.Web.MemberController;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
public class RoomResponseDto {

    private String name;

    private String password;

    private Long LeaderId;

    private List<MemberResponseDto> memberList;

    private List<RestaurantResponseDto> restaurantList;

    @Builder
    public RoomResponseDto(@JsonProperty("id")Long id,@JsonProperty("name") String name,@JsonProperty("password") String password,@JsonProperty("leaderId") Long leaderId,@JsonProperty("memberList") List<MemberResponseDto> memberList,@JsonProperty("restaurantList") List<RestaurantResponseDto> restaurantList) {
        this.name = name;
        this.password = password;
        LeaderId = leaderId;
        this.memberList = memberList;
        this.restaurantList = restaurantList;
    }

    public Room toEntity()
    {
        return Room.builder()
                .name(name)
                .password(password)
                .build();
    }
}
