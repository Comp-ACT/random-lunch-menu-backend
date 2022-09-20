package com.TeamSk.JMC.Web.Dto.roomDto;

import com.TeamSk.JMC.Domain.Room.Room;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RoomJoinDto {

    private Long roomId;
    private Long memberId;

    private String password;

    public RoomJoinDto(@JsonProperty("roomId") Long roomId,@JsonProperty("memberId") Long memberId,@JsonProperty("password") String password) {
        this.roomId = roomId;
        this.memberId = memberId;
        this.password = password;
    }
}
