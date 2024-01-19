package com.AppointmentBooking.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class resttemplateConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public PasswordEncoder getencodedpassword() {

        return new BCryptPasswordEncoder();
    }
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
