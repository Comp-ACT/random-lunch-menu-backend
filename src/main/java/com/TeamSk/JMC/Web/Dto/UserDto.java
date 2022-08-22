package com.TeamSk.JMC.Web.Dto;

import com.TeamSk.JMC.Domain.User.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class UserDto {

    private String name;
    private String email;

    public UserDto(Users entity)
    {
        this.name = entity.getName();
        this.email = entity.getEmail();
    }

    public Users toEntity() {
        return Users.builder()
                .name(name)
                .email(email)
                .build();
    }
}
