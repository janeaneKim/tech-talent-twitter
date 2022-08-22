package com.tts.techtalenttwitter2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //says that this class is a class that holds cofig info

//definitions on how to create autowired objects can be added here 
//ie. tell SpringBoot hot to make bCryptPassword = make bean
public class WebMvcConfiguration implements WebMvcConfigurer {
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = 
            new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}
