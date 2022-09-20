package com.TeamSk.JMC.Web.Dto.MemberDto;

import com.TeamSk.JMC.Domain.Member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private Long id;
    private String name;
    private String email;
    private String profileImageURL;

    @Builder
    public MemberResponseDto(Long id, String name, String email, String profileImageURL) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileImageURL = profileImageURL;
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .profileImageURL(profileImageURL)
                .build();
    }
}
