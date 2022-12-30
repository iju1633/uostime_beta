package com.example.uostime_beta;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.logging.Logger;

@EnableJpaAuditing // Auditing 기능 활성화
@SpringBootApplication
@EnableScheduling
public class UostimeBetaApplication {

    //final static org.slf4j.Logger log = LoggerFactory.getLogger(UostimeBetaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(UostimeBetaApplication.class, args);

        //log.info("please");
    }

}
