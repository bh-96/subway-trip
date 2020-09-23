package com.subwaytrip.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:config.properties")
@SpringBootApplication
public class SubwayTripApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubwayTripApplication.class, args);
    }

}
