package com.meta.air_jet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AirJetApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirJetApplication.class, args);
    }
}
