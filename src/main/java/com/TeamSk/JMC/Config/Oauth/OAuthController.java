package com.TeamSk.JMC.Config.Oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/oauth")
public class OAuthController {
    @GetMapping("/token")
    public String token(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            log.info(cookie.toString());
        }
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.info(cookie.toString());
            }
        } else {
            log.info("/oauth/token cookie null");
        }

        return "hello";
    }

}
