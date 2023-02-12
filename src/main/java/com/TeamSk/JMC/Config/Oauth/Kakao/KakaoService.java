package com.TeamSk.JMC.Config.Oauth.Kakao;

import com.TeamSk.JMC.Domain.Member.Member;
import com.TeamSk.JMC.Service.Login.LoginService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class KakaoService {

    private final LoginService loginService;
    private final String clientId = "450bbd68a5b2bcb5e9e238e18525798a";
    private final String redirectURL = "http://localhost:8080/kakao/login";

    private static void setUpTransaction(String accessToken, HttpURLConnection urlConnection) throws ProtocolException {
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        urlConnection.setRequestProperty("Authorization", "Bearer " + accessToken); //전송할 header 작성, access_token전송
    }

    public String getKakaoAccessToken(String code) throws IOException {
        //code는 토큰을 받기 위한 요청에 필요한 인가 코드이다.

        HashMap<String, String> token = new HashMap<>();
        HttpURLConnection urlConnection = null;
        BufferedWriter bw = null;
        try {
            urlConnection = getHttpURLConnection();
            bw = requestAuthorizationCode(code, urlConnection);

            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
                JsonObject jsonObject = parseResponseMessage(urlConnection);
                token.put("access_token", jsonObject.get("access_token").getAsString());
                token.put("refresh_token", jsonObject.get("refresh_token").getAsString());

                System.out.println("access_token : " + token.get("access_token"));
                System.out.println("refresh_token : " + token.get("refresh_token"));
            } else {
                System.err.println("Response code: " + responseCode);
                System.err.println("Response message: " + urlConnection.getResponseMessage());
            }
        } catch (IOException e) {
            System.err.println("accessToken을 가져오면서 에러가 발생하였습니다. : " + e.getMessage());
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
            if (bw != null) bw.close();
        }

        bw.close();

        return token.get("access_token");
    }

    public BufferedWriter requestAuthorizationCode(String code, HttpURLConnection urlConnection) throws IOException {
        //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
        String kakaoConfig = "grant_type=authorization_code" +
                "&client_id=" + clientId + // REST_API_KEY 입력
                "&redirect_uri=" + redirectURL + // 인가코드 받은 redirect_uri 입력
                "&code=" + code;
        bw.write(kakaoConfig);
        bw.flush();
        return bw;
    }

    public HttpURLConnection getHttpURLConnection() throws IOException {
        String reqURL = "https://kauth.kakao.com/oauth/token";

        URL url = new URL(reqURL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("POST");
        //POST 요청을 위해 기본값이 false인 setDoOutput을 true로 설정
        //이 URLConnection이 서버에 데이터를 보내는데 사용할 수 있는지 여부를 설정
        urlConnection.setDoOutput(true);
        return urlConnection;
    }

    public void createKakaoUser(String accessToken) {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        Member member = fetchKakaoUserInfo(accessToken, reqURL);
        loginService.login(member);
    }

    private Member fetchKakaoUserInfo(String accessToken, String reqURL) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(reqURL);
            urlConnection = (HttpURLConnection) url.openConnection();

            setUpTransaction(accessToken, urlConnection);

            int responseCode = urlConnection.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            JsonObject jsonObject = parseResponseMessage(urlConnection);
            return extractMemberInfo(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
        }
        return null;
    }

    private JsonObject parseResponseMessage(HttpURLConnection urlConnection) throws IOException {
        JsonReader jsonReader = new JsonReader(new InputStreamReader(urlConnection.getInputStream()));
        jsonReader.setLenient(true);
        return JsonParser.parseReader(jsonReader).getAsJsonObject();
    }

    private Member extractMemberInfo(JsonObject jsonObject) {
        Long id = jsonObject.get("id").getAsLong();
        JsonObject kakaoAccount = jsonObject.get("kakao_account").getAsJsonObject();
        boolean hasEmail = kakaoAccount.get("has_email").getAsBoolean();
        JsonElement jsonElement = kakaoAccount.get("is_email_valid");
        boolean isEmailValid = false;
        if (jsonElement != null)
            isEmailValid = jsonElement.getAsBoolean();

        String email = "";
        if (hasEmail && isEmailValid) {
            email = kakaoAccount.get("email").getAsString();
        }
        JsonObject profile = kakaoAccount.get("profile").getAsJsonObject();
        String nickname = profile.get("nickname").getAsString();
        String profileImageURL = profile.get("thumbnail_image_url").getAsString();

        System.out.println("id : " + id);
        System.out.println("email : " + email);
        System.out.println("nickname = " + nickname);
        System.out.println("profileImageURL = " + profileImageURL);
        return Member.builder()
                .name(nickname)
                .email(email)
                .profileImageURL(profileImageURL)
                .build();
    }
}
