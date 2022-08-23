package com.TeamSk.JMC.Service.Login;

import com.TeamSk.JMC.Domain.User.Member;
import com.TeamSk.JMC.Domain.User.MemberRepository;
import com.TeamSk.JMC.Web.Dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.h2.engine.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
