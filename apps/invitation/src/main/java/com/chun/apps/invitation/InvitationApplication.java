package com.chun.apps.invitation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.chun.modules.crud"})
@EnableJpaRepositories(basePackages = {"com.chun.modules.crud"})
public class InvitationApplication {
    public static void main(String[] args) {
        SpringApplication.run(InvitationApplication.class, args);
    }
}
