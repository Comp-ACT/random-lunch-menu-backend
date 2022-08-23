package com.TeamSk.JMC.Config.Oauth.Kakao;

import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.TeamSk.JMC.Config.Oauth.Kakao.KakaoService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/kakao")
public class KakaoController {
    private final KakaoService ks;
    @ResponseBody
    @GetMapping("/login")
    public void kakaoCallback(@RequestParam String code) {
        System.out.println(code);
        String access_Token = ks.getKakaoAccessToken(code);
        ks.createKakaoUser(access_Token);
    }
}
