package com.wap.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ApiApplication {
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

}
