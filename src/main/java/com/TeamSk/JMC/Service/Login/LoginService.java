package com.TeamSk.JMC.Service.Login;

import com.TeamSk.JMC.Domain.User.Users;
import com.TeamSk.JMC.Domain.User.UserRepository;
import com.TeamSk.JMC.Web.Dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.h2.engine.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserRepository userRepository;

    public boolean login(Users user)
    {

        userRepository.save(user);


        return true;
    }


}
