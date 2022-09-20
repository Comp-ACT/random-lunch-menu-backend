package com.TeamSk.JMC.Service.Member;

import com.TeamSk.JMC.Domain.Member.Member;
import com.TeamSk.JMC.Domain.Member.MemberRepository;
import com.TeamSk.JMC.Web.Dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto findById(Long userId)
    {
        Member entity = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id =" + userId));
        return new MemberDto(entity);
    }
}
