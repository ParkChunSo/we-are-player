package com.chun.apps.club.config;

import com.chun.modules.crud.member.repository.MemberCrudRepository;
import com.chun.modules.crud.club.repository.ClubCrudRepository;
import com.chun.modules.crud.club.repository.ClubMemberCrudRepository;
import com.chun.modules.crud.club.service.ClubCrudService;
import com.chun.modules.crud.club.service.ClubMemberCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CrudConfig {
    private final ClubCrudRepository clubCrudRepository;
    private final ClubMemberCrudRepository clubMemberCrudRepository;
    private final MemberCrudRepository memberCrudRepository;

    @Bean
    public ClubCrudService clubCrudService(){
        return new ClubCrudService(clubCrudRepository, clubMemberCrudRepository, memberCrudRepository);
    }

    @Bean
    public ClubMemberCrudService clubMemberCrudService(){
        return new ClubMemberCrudService(clubCrudRepository, clubMemberCrudRepository, memberCrudRepository);
    }
}
