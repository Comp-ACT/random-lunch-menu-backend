package com.TeamSk.JMC.Web.Dto.roomDto;

import com.TeamSk.JMC.Domain.Room.Room;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RoomMakingDto {

    private String name;

    private String password;

    private Long leaderId;
    @Builder
    public RoomMakingDto(@JsonProperty("name") String name,@JsonProperty("password") String password,@JsonProperty("leaderId") Long leaderId)
    {
        this.name = name;
        this.password = password;
        this.leaderId = leaderId;
    }

    public Room toEntity()
    {
        return Room.builder()
                .name(name)
                .password(password)
                .leaderId(leaderId)
                .build();
    }


}
