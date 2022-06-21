package com.masters.uj.otp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class OtpServiceApplication {

    private static final Logger LOG = LoggerFactory.getLogger(OtpServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OtpServiceApplication.class, args);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> onApplicationReadyEventListener(ServerProperties serverProperties) {

        return (evt) -> {
            Integer port = serverProperties.getPort();

            LOG.info("OTP-DS started: http://localhost:{}/otp/swagger-ui.html to use otp-service", port);
        };
    }

}
