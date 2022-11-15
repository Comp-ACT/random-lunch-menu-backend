package com.TeamSk.JMC.Service.Member;

import com.TeamSk.JMC.Domain.Member.Member;
import com.TeamSk.JMC.Domain.Member.MemberRepository;
import com.TeamSk.JMC.Exception.handler.Handler;
import com.TeamSk.JMC.Web.Dto.MemberDto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service

public class MemberService {

    private final MemberRepository memberRepository;
    private final Handler handler;

    public MemberDto findById(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        handler.memberNotFoundExceptionHandler(memberId, memberOptional);
        return new MemberDto(memberOptional.get());
    }
}
