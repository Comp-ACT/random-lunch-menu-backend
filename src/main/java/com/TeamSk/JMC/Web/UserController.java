package com.TeamSk.JMC.Web;

import com.TeamSk.JMC.Service.Users.UserService;
import com.TeamSk.JMC.Web.Dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService usersService;

    @GetMapping("/users/{userId}")
    public UserDto findById(@PathVariable Long userId)
    {

        return usersService.findById(userId);
    }
}
