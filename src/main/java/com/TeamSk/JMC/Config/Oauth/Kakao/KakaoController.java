package com.TeamSk.JMC.Config.Oauth.Kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/kakao")
public class KakaoController {
    private final KakaoService kakaoService;
    @ResponseBody
    @GetMapping("/login")
    public void kakaoCallback(@RequestParam String code) {
        System.out.println(code);
        String access_Token = kakaoService.getKakaoAccessToken(code);
        kakaoService.createKakaoUser(access_Token);
    }
}
