package com.TeamSk.JMC.Web.Dto.MemberDto;

import com.TeamSk.JMC.Domain.Member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberHashMapDto {
    private Long roomMemberId;
    private Long memberId;
    private String name;
    private String email;
    private String profileImageURL;

    @Builder
    public MemberHashMapDto(Long roomMemberId,Long memberId, String name, String email, String profileImageURL) {
        this.roomMemberId = roomMemberId;
        this.memberId = memberId;
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