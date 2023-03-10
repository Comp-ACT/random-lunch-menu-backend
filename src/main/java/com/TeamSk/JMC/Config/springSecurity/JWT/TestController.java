package com.TeamSk.JMC.Config.springSecurity.JWT;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/test")
    public String test() {

        return "<h1>test 통과</h1>";
    }
}
