package com.TeamSk.JMC.Web;

import com.TeamSk.JMC.Service.Login.LoginService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

}
