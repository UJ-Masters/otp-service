package com.masters.uj.otp;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
@ComponentScan
public class OtpConfig {

    /*@Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.setMessageConverters(Arrays
                .asList(new StringHttpMessageConverter(),
                        new FormHttpMessageConverter()));

        return restTemplate;
    }*/

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        RestTemplate restTemplate = builder.build();

        restTemplate
                .setMessageConverters(Arrays
                        .asList(new StringHttpMessageConverter(),
                                new FormHttpMessageConverter(), new MappingJackson2HttpMessageConverter()));

        return builder.build();
    }
}
