package com.TeamSk.JMC.Web.Dto.MemberDto;

import com.TeamSk.JMC.Domain.Member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDto {

    private String name;
    private String email;
    private String profileImageURL;

    public MemberDto(Member entity)
    {
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.profileImageURL = entity.getProfileImageURL();
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .profileImageURL(profileImageURL)
                .build();
    }
}
