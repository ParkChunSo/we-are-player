package com.chun.apps.member.config;

import com.chun.modules.crud.member.repository.MemberCrudRepository;
import com.chun.modules.crud.member.service.MemberCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CrudConfig {
    private final MemberCrudRepository memberCrudRepository;

    @Bean
    public MemberCrudService clubCrudService(){
        return new MemberCrudService(memberCrudRepository);
    }
}
