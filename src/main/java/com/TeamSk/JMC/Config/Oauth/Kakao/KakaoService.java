package com.TeamSk.JMC.Config.Oauth.Kakao;

import com.TeamSk.JMC.Domain.Member.Member;
import com.TeamSk.JMC.Service.Login.LoginService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@RequiredArgsConstructor
@Service
public class KakaoService {

    private final LoginService loginService;

    public String getKakaoAccessToken (String code) {
        String accessToken = "";
        String refreshToken = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=7ef2e03ad8fa35a0bb154e58e9d393d7"); // TODO REST_API_KEY 입력
            sb.append("&redirect_uri=http://localhost:8080/kakao/login"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = urlConnection.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            JsonReader jsonReader = new JsonReader(new InputStreamReader(urlConnection.getInputStream()));
            jsonReader.setLenient(true);
            while(jsonReader.peek() != JsonToken.END_DOCUMENT)
            {
                JsonObject jsonObject = JsonParser.parseReader(jsonReader).getAsJsonObject();
                accessToken = jsonObject.get("access_token").getAsString();
                refreshToken = jsonObject.get("refresh_token").getAsString();

            }


            System.out.println("access_token : " + accessToken);
            System.out.println("refresh_token : " + refreshToken);

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    public void createKakaoUser(String accessToken) {

        String reqURL = "https://kapi.kakao.com/v2/user/me";

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //http 트랜잭션 작성
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Authorization", "Bearer " + accessToken); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = urlConnection.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기

            JsonReader jsonReader = new JsonReader(new InputStreamReader(urlConnection.getInputStream()));
            jsonReader.setLenient(true);

            //Json 파싱
            String email = "";
            Long id = 0L;
            String nickname = "";
            while(jsonReader.peek() != JsonToken.END_DOCUMENT)
            {
                JsonObject jsonObject = JsonParser.parseReader(jsonReader).getAsJsonObject();
                System.out.println(jsonObject.toString());
                id = jsonObject.get("id").getAsLong();
                boolean hasEmail = jsonObject.get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
                boolean isEmailValid = jsonObject.get("kakao_account").getAsJsonObject().get("is_email_valid").getAsBoolean();
                boolean isEmailVerified = jsonObject.get("kakao_account").getAsJsonObject().get("is_email_verified").getAsBoolean();
                if(hasEmail && isEmailValid && isEmailValid){
                    email = jsonObject.get("kakao_account").getAsJsonObject().get("email").getAsString();
                }
                //지금은 동의 안받고 출력 나중에 동의 받았는지 아닌지 확인 후 출력해야함
                nickname = jsonObject.get("kakao_account").getAsJsonObject().get("profile").getAsJsonObject().get("nickname").getAsString();
            }

            System.out.println("id : " + id);
            System.out.println("email : " + email);
            System.out.println("nickname = " + nickname);

            Member user = Member.builder()
                    .name(nickname)
                    .email(email)
                    .build();

            loginService.login(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
