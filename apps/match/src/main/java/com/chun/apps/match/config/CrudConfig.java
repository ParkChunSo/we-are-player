package com.chun.apps.match.config;

import com.chun.crud.member.repository.MemberCrudRepository;
import com.chun.crud.repository.ClubCrudRepository;
import com.chun.crud.repository.ClubMemberCrudRepository;
import com.chun.crud.repository.MatchCrudRepository;
import com.chun.crud.service.ClubCrudService;
import com.chun.crud.service.ClubMemberCrudService;
import com.chun.crud.service.MatchCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CrudConfig {
    private final ClubCrudRepository clubCrudRepository;
    private final ClubMemberCrudRepository clubMemberCrudRepository;
    private final MemberCrudRepository memberCrudRepository;
    private final MatchCrudRepository matchCrudRepository;

    @Bean
    public ClubCrudService clubCrudService(){
        return new ClubCrudService(clubCrudRepository, clubMemberCrudRepository, memberCrudRepository);
    }

    @Bean
    public ClubMemberCrudService clubMemberCrudService(){
        return new ClubMemberCrudService(clubCrudRepository, clubMemberCrudRepository, memberCrudRepository);
    }

    @Bean
    public MatchCrudService matchCrudService(){
        return new MatchCrudService(matchCrudRepository, memberCrudRepository, clubCrudRepository);
    }
}
