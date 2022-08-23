package com.TeamSk.JMC.Web;

import com.TeamSk.JMC.Service.Mebmer.MemberService;
import com.TeamSk.JMC.Web.Dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final MemberService usersService;

    @GetMapping("/users/{userId}")
    public UserDto findById(@PathVariable Long userId)
    {

        return usersService.findById(userId);
    }
}
