package com.example.warproject.config;

//시큐리티 설정 파일

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SercurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http로부터 request를 받을때
        http.authorizeRequests()
                .mvcMatchers("/", "/board").permitAll() //Url이 /,/board로 들어올 때 권한을 모두 허용한다(permitAll()).
                .mvcMatchers("/index").hasRole("ADMIN") //admin으로 들어올 때 ADMIN 이란 권한이 필요하다.
                .anyRequest().authenticated() //어떤 요청이 들어오더라도 (위 제외) 인증이 필요하다.
                .and()
            .formLogin() //security가 제공하는 기본적인 로그인 화면, 로그인 기능, 로그아웃 기능을 제공한다.
                .and()
            .httpBasic();
    }

    //1명의 로그인 유저가 아닌 복수의 유저가 로그인 가능하도록 설정
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("jake").password("{noop}123").roles("USER")
                .and()
                .withUser("admin").password("{noop}123").roles("ADMIN"); //{noop}은 인코딩 설정을 안한다는것을 의미
    }
}
