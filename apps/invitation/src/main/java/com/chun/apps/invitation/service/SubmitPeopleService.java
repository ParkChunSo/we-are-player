package com.chun.apps.invitation.service;


import com.chun.apps.invitation.dtos.request.SubmitPeopleSaveReqDto;
import com.chun.apps.invitation.dtos.request.SubmitPeopleUpdateReqDto;
import com.chun.commons.dtos.invitation.SubmitPeopleResDto;
import com.chun.commons.enums.ClubMemberType;
import com.chun.commons.enums.SubmitState;
import com.chun.commons.errors.exception.AuthorizationException;
import com.chun.crud.entitys.Club;
import com.chun.crud.entitys.ClubMember;
import com.chun.crud.invitation.member.entity.InvitationMember;
import com.chun.crud.invitation.member.entity.SubmitMember;
import com.chun.crud.invitation.member.service.InvitationMemberCrudService;
import com.chun.crud.invitation.member.service.SubmitMemberCrudService;
import com.chun.crud.invitation.member.util.InvitationMemberConvertor;
import com.chun.modules.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubmitPeopleService {
    private final JwtTokenProvider jwtTokenProvider;
    private final InvitationMemberCrudService invitationMemberCrudService;
    private final SubmitMemberCrudService submitMemberCrudService;

    public SubmitPeopleResDto find(Long id){
        SubmitMember submit = submitMemberCrudService.find(id);
        return InvitationMemberConvertor.toSubmitMemberResDto(submit);
    }

    public void save(SubmitPeopleSaveReqDto dto) {
        submitMemberCrudService.save(dto.toSubmitPeopleSaveDto());
    }

    @Transactional
    public void update(String token, SubmitPeopleUpdateReqDto dto) {
        if(dto.getState().equals(SubmitState.CONFIRM) || dto.getState().equals(SubmitState.REJECT)){
            InvitationMember invitation = invitationMemberCrudService.find(dto.getInvitationId());
            String requestId = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(token));
            if(!isLeader(requestId, invitation.getClub()))
                throw new AuthorizationException();
        }

        submitMemberCrudService.update(dto.toSubmitPeopleUpdateDto());
    }

    public void delete(Long id){
        submitMemberCrudService.delete(id);
    }

    private boolean isLeader(String requestId, Club club){
        return club.getClubMembers().stream()
                .filter(clubMember -> clubMember.getClubMemberType().equals(ClubMemberType.LEADER))
                .map(ClubMember::getMember)
                .anyMatch(member -> member.getId().equals(requestId));
    }
}
