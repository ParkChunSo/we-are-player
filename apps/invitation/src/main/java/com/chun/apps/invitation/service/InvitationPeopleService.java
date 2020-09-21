package com.chun.apps.invitation.service;

import com.chun.apps.invitation.dtos.request.InvitationPeopleSaveReqDto;
import com.chun.apps.invitation.dtos.request.InvitationPeopleUpdateReqDto;
import com.chun.commons.dtos.invitation.InvitationPeopleResDto;
import com.chun.commons.enums.ClubMemberType;
import com.chun.commons.enums.InvitationType;
import com.chun.commons.enums.MemberRole;
import com.chun.commons.errors.exception.AccessDeniedAuthenticationException;
import com.chun.modules.crud.club.entitys.Club;
import com.chun.modules.crud.club.entitys.ClubMember;
import com.chun.modules.crud.invitation.member.entity.InvitationMember;
import com.chun.modules.crud.invitation.member.service.InvitationMemberCrudService;
import com.chun.modules.crud.invitation.member.util.InvitationMemberConvertor;
import com.chun.modules.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvitationPeopleService {
    private final JwtTokenProvider jwtTokenProvider;
    private final InvitationMemberCrudService invitationMemberCrudService;

    public InvitationPeopleResDto find(Long id) {
        InvitationMember entity = invitationMemberCrudService.find(id);
        return InvitationMemberConvertor.toInvitationMemberResDto(entity);
    }
    public List<InvitationPeopleResDto> findAll(InvitationType type){
        return invitationMemberCrudService.findAll(type).stream()
                .map(InvitationMemberConvertor::toInvitationMemberResDto)
                .collect(Collectors.toList());
    }

    public List<InvitationPeopleResDto> findPeopleByLocation(String city, String district) {
        List<InvitationMember> invitations = invitationMemberCrudService.findByLocation(city, district);
        return invitations.stream()
                .map(InvitationMemberConvertor::toInvitationMemberResDto)
                .collect(Collectors.toList());
    }

    public void save(InvitationPeopleSaveReqDto dto) {
        invitationMemberCrudService.save(dto.toInvitationPeopleSaveDto());
    }

    public void update(String token, InvitationPeopleUpdateReqDto dto) {
        InvitationMember invitation = invitationMemberCrudService.find(dto.getId());

        if (!checkLeaderOrAdmin(token, invitation)) {
            throw new AccessDeniedAuthenticationException();
        }

        invitationMemberCrudService.update(dto.toInvitationPeopleUpdateDto());
    }

    public void delete(String token, long id){
        InvitationMember invitation = invitationMemberCrudService.find(id);

        if (!checkLeaderOrAdmin(token, invitation))
            throw new AccessDeniedAuthenticationException();

        invitationMemberCrudService.delete(id);
    }

    private boolean checkLeaderOrAdmin(String resolvedToken, InvitationMember invitation) {
        String requestId = jwtTokenProvider.getUsername(resolvedToken);

        return  isLeader(requestId, invitation.getClub())
                || isAdmin(resolvedToken);
    }

    private boolean isAdmin(String token) {
        return jwtTokenProvider.hasRole(jwtTokenProvider.resolveToken(token), MemberRole.ADMIN);
    }

    private boolean isLeader(String requestId, Club club){
        return club.getClubMembers().stream()
                .filter(clubMember -> clubMember.getClubMemberType().equals(ClubMemberType.LEADER))
                .map(ClubMember::getMember)
                .anyMatch(member -> member.getId().equals(requestId));
    }
}
