package com.TeamSk.JMC.Web;

import com.TeamSk.JMC.Service.Member.MemberService;
import com.TeamSk.JMC.Web.Dto.MemberDto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService usersService;

    @GetMapping("/users/{userId}")
    public MemberDto findById(@PathVariable Long userId)
    {
        return usersService.findById(userId);
    }


}
