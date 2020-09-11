package com.chun.apps.invitation.config;

import com.chun.crud.invitation.match.repository.InvitationMatchCrudRepository;
import com.chun.crud.invitation.match.repository.SubmitMatchCrudRepository;
import com.chun.crud.invitation.member.respository.InvitationMemberCrudRepository;
import com.chun.crud.invitation.member.respository.SubmitMemberCrudRepository;
import com.chun.crud.member.repository.MemberCrudRepository;
import com.chun.crud.repository.*;
import com.chun.crud.invitation.match.service.InvitationMatchCrudService;
import com.chun.crud.invitation.member.service.InvitationMemberCrudService;
import com.chun.crud.invitation.match.service.SubmitMatchCrudService;
import com.chun.crud.invitation.member.service.SubmitMemberCrudService;
import com.chun.crud.service.ClubCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CrudConfig {
    private final MatchCrudRepository matchCrudRepository;
    private final ClubCrudRepository clubCrudRepository;
    private final MemberCrudRepository memberCrudRepository;
    private final InvitationMatchCrudRepository invitationMatchCrudRepository;
    private final InvitationMemberCrudRepository invitationMemberCrudRepository;
    private final SubmitMatchCrudRepository submitMatchCrudRepository;
    private final SubmitMemberCrudRepository submitMemberCrudRepository;
    private final ClubMemberCrudRepository clubMemberCrudRepository;


    @Bean
    public InvitationMatchCrudService invitationMatchCrudService(){
        return new InvitationMatchCrudService(clubCrudRepository, invitationMatchCrudRepository);
    }
    @Bean
    public SubmitMatchCrudService submitMatchCrudService(){
        return new SubmitMatchCrudService(invitationMatchCrudRepository, submitMatchCrudRepository, clubCrudRepository, matchCrudRepository);
    }
    @Bean
    public InvitationMemberCrudService invitationMemberCrudService(){
        return new InvitationMemberCrudService(clubCrudRepository, invitationMemberCrudRepository);
    }

    @Bean
    public SubmitMemberCrudService submitMemberCrudService(){
        return new SubmitMemberCrudService(invitationMemberCrudRepository, submitMemberCrudRepository, memberCrudRepository, clubMemberCrudRepository);
    }
}
