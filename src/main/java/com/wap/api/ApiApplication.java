package com.wap.api;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ApiApplication implements ApplicationRunner {
    private static final String PROPERTIES =
            "spring.config.location="
                    +"classpath:application.yml";
//                    +"classpath:/application-dev.yml"
//                    +",classpath:/aws.yml";
    public static void main(String[] args) {
        new SpringApplicationBuilder(ApiApplication.class)
//                .properties(PROPERTIES)
                .run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        System.out.println("phae =>" + System.getProperty("spring.profiles.active"));
        System.out.println("phae =>" + System.getenv("--spring.profiles.active"));
    }
}
