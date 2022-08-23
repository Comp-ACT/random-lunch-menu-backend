package com.TeamSk.JMC.Web.Dto;

import com.TeamSk.JMC.Domain.Member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDto {

    private String name;
    private String email;

    public MemberDto(Member entity)
    {
        this.name = entity.getName();
        this.email = entity.getEmail();
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .build();
    }
}
