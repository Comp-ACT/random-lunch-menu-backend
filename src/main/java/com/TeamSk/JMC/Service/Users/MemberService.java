package com.TeamSk.JMC.Service.Users;

import com.TeamSk.JMC.Domain.User.Member;
import com.TeamSk.JMC.Domain.User.UserRepository;
import com.TeamSk.JMC.Web.Dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserDto findById(Long userId)
    {
        Member entity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id =" + userId));
        return new UserDto(entity);
    }
}
