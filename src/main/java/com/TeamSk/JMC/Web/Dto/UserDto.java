package com.TeamSk.JMC.Web.Dto;

import com.TeamSk.JMC.Domain.Member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;

    public UserDto(Member entity)
    {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .build();
    }
}
