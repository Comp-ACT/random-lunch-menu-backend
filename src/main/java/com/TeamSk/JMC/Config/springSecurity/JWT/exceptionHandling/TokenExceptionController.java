package com.TeamSk.JMC.Config.springSecurity.JWT.exceptionHandling;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenExceptionController {
    @GetMapping("/exception/entrypoint")
    public void entryPoint() {
        throw new CustomJWTException(ErrorCode.NO_LOGIN);
    }

}
