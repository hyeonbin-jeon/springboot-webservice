package com.hyeon.springbootwebservice.config.auth;

import com.hyeon.springbootwebservice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.CustomUserTypesOAuth2UserService;

@RequiredArgsConstructor
@EnableWebSecurity  //spring security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserTypesOAuth2UserService customUserTypesOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()   //h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                    .authorizeRequests()   //URL별 권한 관리를 설정하는 옵션의 시작점
                    //권한 관리 대상을 지정하는 옵션, URL, HTTP메소드별로 관리가 가능
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()  //모든 권한
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())  //USER권한
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customUserTypesOAuth2UserService);
    }
}
