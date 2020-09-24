package com.chun.apps.invitation.config;

import com.chun.modules.crud.invitation.match.repository.InvitationMatchCrudRepository;
import com.chun.modules.crud.invitation.match.repository.SubmitMatchCrudRepository;
import com.chun.modules.crud.invitation.member.respository.InvitationMemberCrudRepository;
import com.chun.modules.crud.invitation.member.respository.SubmitMemberCrudRepository;
import com.chun.modules.crud.member.repository.MemberCrudRepository;
import com.chun.modules.crud.invitation.match.service.InvitationMatchCrudService;
import com.chun.modules.crud.invitation.member.service.InvitationMemberCrudService;
import com.chun.modules.crud.invitation.match.service.SubmitMatchCrudService;
import com.chun.modules.crud.invitation.member.service.SubmitMemberCrudService;
import com.chun.modules.crud.club.repository.ClubCrudRepository;
import com.chun.modules.crud.club.repository.ClubMemberCrudRepository;
import com.chun.modules.crud.repository.MatchCrudRepository;
import com.chun.modules.crud.club.service.ClubCrudService;
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

    @Bean
    public ClubCrudService clubCrudService(){
        return new ClubCrudService(clubCrudRepository,clubMemberCrudRepository, memberCrudRepository);
    }
}
