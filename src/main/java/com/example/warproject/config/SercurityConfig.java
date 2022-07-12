package com.example.warproject.config;

//시큐리티 설정 파일

import com.example.warproject.Point.AuthenticationEntryPointHandler;
import com.example.warproject.handler.CustomAuthenticationFailureHandler;
import com.example.warproject.handler.CustomUrlAuthenticationSuccessHandler;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SercurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        http
                .cors()
                .configurationSource(corsConfigurationSource())//cors 교차 출처 리소스 서로 출처가 다른 웹 애플리케이션에서 자원을 공유
            .and()
                /*
                CSRF공격 Cross Site Request Forgery 인터넷 사용자가 자신의 의지와는 무관하게
                공격자가 의도한 행위(수정,삭제,등록 등)을 특정 웹사이트에 요청하게 만든는 공격.
                *위조 요청을 전송하는 서비스에 희생자가 로그인후 해커가 만든 피싱 사이트에 접속속                 */
                .csrf().disable()//disable안해주면 토큰 포함시켜줘야함
                .sessionManagement()//세션정책 설정
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)//스프링시큐리티가 항상 세션을 생성
            .and()
                .authorizeRequests()
                    .antMatchers("/", "/user", "/search","/board", "/picture/**","/api/**","/play/**", "download/**", "/picture/**", "/login1/**").permitAll()//이 URL들은 아무나 접근가능
                    .antMatchers("/admin").hasRole("ADMIN") //admin으로 들어올 때 ADMIN 이란 권한이 필요하다.
                    .anyRequest().authenticated() //어떤 요청이 들어오더라도 (위 제외) 인증이 필요하다.
            .and()
                .formLogin()
                    .loginPage("/") //로그인 페이지 링크
                    .loginProcessingUrl("/login1")//로그인프로세스 URL
                    .successHandler(authenticationSuccessHandler())
                    .failureHandler(authenticationFailureHandler())
                    .usernameParameter("username")
                    .passwordParameter("password")
//                    .defaultSuccessUrl("/board")// 로그인 성공 후 리다이렉트 주소
            .and()
                .exceptionHandling()//예외처리기능
                    .authenticationEntryPoint(authenticationEntryPointHandler)//토큰확인
                    .accessDeniedHandler(webAccessDeniedHandler)//에러확인
            .and()
                .logout()//로그아웃
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")//로그아웃 성공시 이동 URL
                    .invalidateHttpSession(true);//로그아웃후 세션날리기
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
        AuthenticationProvider 구현체
         사용자 세부 서비스 설정 오버라이딩
         */
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder);
    }

    //CORS 허용 적용
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:8080");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new CustomUrlAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new CustomAuthenticationFailureHandler();
    }
}

