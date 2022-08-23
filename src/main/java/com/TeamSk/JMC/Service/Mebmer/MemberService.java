package com.TeamSk.JMC.Service.Mebmer;

import com.TeamSk.JMC.Domain.User.Member;
import com.TeamSk.JMC.Domain.User.MemberRepository;
import com.TeamSk.JMC.Web.Dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository userRepository;

    public MemberDto findById(Long userId)
    {
        Member entity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id =" + userId));
        return new MemberDto(entity);
    }
}
