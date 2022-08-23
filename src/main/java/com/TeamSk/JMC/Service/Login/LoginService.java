package com.TeamSk.JMC.Service.Login;

import com.TeamSk.JMC.Domain.User.Member;
import com.TeamSk.JMC.Domain.User.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final MemberRepository userRepository;

    public boolean login(Member member)
    {


        userRepository.save(member);


        return true;
    }


}
