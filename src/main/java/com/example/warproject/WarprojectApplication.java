package com.example.warproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class WarprojectApplication {

    //어플리케이션에 PasswordEncoder을 Bean으로 등록함으로써 어디서든 사용가능하게
    @Bean
    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
            return PasswordEncoderFactories.createDelegatingPasswordEncoder();//Security에서 권장하는 Password Encoder
    }

    public static void main(String[] args) {
        SpringApplication.run(WarprojectApplication.class, args);
    }

}
