package com.TeamSk.JMC.Config.Oauth.Kakao;

import com.TeamSk.JMC.Config.springSecurity.JWT.JwtTokenProvider;
import com.TeamSk.JMC.Domain.Member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class KakaoController {
    private final JwtTokenProvider jwtTokenProvider;
    private final KakaoService kakaoService;

    @ResponseBody
    @GetMapping("/kakao/login/asdf")
    public String kakaoCallback(@RequestParam String code, HttpServletResponse response) throws IOException {
        System.out.println(code);
        String access_Token = kakaoService.getKakaoAccessToken(code);
        Member member = kakaoService.createKakaoUser(access_Token);
        String token = jwtTokenProvider.createToken(member.getEmail());
        response.setHeader("Authorization", token);

        return token;
    }


}
