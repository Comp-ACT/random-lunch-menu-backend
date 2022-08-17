package com.TeamSk.JMC.Web.Dto;

import com.TeamSk.JMC.Domain.User.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;

    public UserDto(Users entity)
    {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public Users toEntity() {
        return Users.builder()
                .name(name)
                .build();
    }
}
