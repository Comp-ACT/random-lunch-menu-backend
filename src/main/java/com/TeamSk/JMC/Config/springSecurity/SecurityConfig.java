package com.TeamSk.JMC.Config.springSecurity;

import com.TeamSk.JMC.Config.Oauth.CustomOAuth2UserService;
import com.TeamSk.JMC.Config.Oauth.HttpCookieOAuth2AuthorizationRequestRepository;
import com.TeamSk.JMC.Config.Oauth.OAuth2AuthenticationFailureHandler;
import com.TeamSk.JMC.Config.springSecurity.JWT.JwtAuthenticationFilter;
import com.TeamSk.JMC.Config.springSecurity.JWT.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    // authenticationManager를 Bean 등록합니다.
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenProvider);
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository HttpCookieOAuth2AuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .httpBasic()
                .disable()
                .formLogin()
                .disable() //spring security 에서 기본적으로 제공하는 로그인 폼 안씀
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth/authorize/**")
                .authorizationRequestRepository(HttpCookieOAuth2AuthorizationRequestRepository())
                .and()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/**")
                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService) //OAuth2 로그인 성공 시, 후 작업을 넘긴다.
                .and()
//                    .successHandler(successHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler)
                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
