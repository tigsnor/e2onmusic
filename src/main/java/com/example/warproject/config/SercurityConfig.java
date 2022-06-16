package com.example.warproject.config;

//시큐리티 설정 파일

import com.example.warproject.Point.AuthenticationEntryPointHandler;
import com.example.warproject.handler.WebAccessDeniedHandler;
import com.example.warproject.model.Member;
import com.example.warproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SercurityConfig extends WebSecurityConfigurerAdapter {

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean()throws Exception{
        return super.authenticationManagerBean();
    }

    private final AuthenticationEntryPointHandler authenticationEntryPointHandler;
    private final WebAccessDeniedHandler webAccessDeniedHandler;

    @Autowired
    private MemberService memberService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http로부터 request를 받을때
        http.cors()
            .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            .and()
                .authorizeRequests()
                    .antMatchers("/", "/user", "/search","/board", "/picture/**").permitAll()
                    .antMatchers("/admin").hasRole("ADMIN") //admin으로 들어올 때 ADMIN 이란 권한이 필요하다.
                    .anyRequest().authenticated() //어떤 요청이 들어오더라도 (위 제외) 인증이 필요하다.
            .and()
                .formLogin() //security가 제공하는 기본적인 로그인 화면, 로그인 기능, 로그아웃 기능을 제공한다.
                    .loginPage("/") //로그인 페이지 링크
                    .loginProcessingUrl("/login1").defaultSuccessUrl("/board")// 로그인 성공 후 리다이렉트 주소
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPointHandler)//토큰확인
                    .accessDeniedHandler(webAccessDeniedHandler)//에러확인
                .and()
                .logout()//로그아웃
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true);//로그아웃후 세션날리기
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService);
    }
//   AntPathRequestMatcher

//    UsernamePasswordAuthenticationFilter
}

