package com.TeamSk.JMC.Config.Oauth.Kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class KakaoController {
    private final KakaoService kakaoService;

    @ResponseBody
    @GetMapping("/kakao/login")
    public String kakaoCallback(@RequestParam String code) throws IOException {
        System.out.println(code);
        String access_Token = kakaoService.getKakaoAccessToken(code);
        kakaoService.createKakaoUser(access_Token);

        return "redirect:/home";
    }

}
