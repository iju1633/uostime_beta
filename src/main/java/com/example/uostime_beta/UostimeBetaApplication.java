package com.example.uostime_beta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing // Auditing 기능 활성화
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class UostimeBetaApplication {

    public static void main(String[] args) {
        SpringApplication.run(UostimeBetaApplication.class, args);
    }

}
