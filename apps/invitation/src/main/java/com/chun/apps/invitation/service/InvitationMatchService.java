package com.chun.apps.invitation.service;


import com.chun.apps.invitation.dtos.request.InvitationMatchSaveReqDto;
import com.chun.apps.invitation.dtos.request.InvitationMatchUpdateReqDto;
import com.chun.commons.dtos.invitation.InvitationMatchResDto;
import com.chun.commons.enums.ClubMemberType;
import com.chun.commons.enums.MemberRole;
import com.chun.commons.errors.exception.AccessDeniedAuthenticationException;
import com.chun.crud.entitys.Club;
import com.chun.crud.entitys.ClubMember;
import com.chun.crud.invitation.match.entity.InvitationMatch;
import com.chun.crud.invitation.match.service.InvitationMatchCrudService;
import com.chun.crud.invitation.match.util.InvitationMatchConvertor;
import com.chun.crud.service.ClubCrudService;
import com.chun.modules.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvitationMatchService {
    private final InvitationMatchCrudService invitationMatchCrudService;
    private final JwtTokenProvider jwtTokenProvider;


    public void save(InvitationMatchSaveReqDto dto) {
        invitationMatchCrudService.save(dto.toInvitationMatchSaveDto());
    }

    public InvitationMatchResDto find(Long id) {
        InvitationMatch entity = invitationMatchCrudService.find(id);
        return InvitationMatchConvertor.toInvitationMatchResDto(entity);
    }

    public List<InvitationMatchResDto> findAll() {
        return invitationMatchCrudService.findAll().stream()
                .map(InvitationMatchConvertor::toInvitationMatchResDto)
                .collect(Collectors.toList());
    }

    public List<InvitationMatchResDto> findByLocation(String city, String district) {
        List<InvitationMatch> invitations = invitationMatchCrudService.findByLocation(city, district);
        return invitations.stream()
                .map(InvitationMatchConvertor::toInvitationMatchResDto)
                .collect(Collectors.toList());
    }

    public void update(String token, InvitationMatchUpdateReqDto dto) {
        InvitationMatch invitation = invitationMatchCrudService.find(dto.getId());

        if (!checkLeaderOrAdmin(token, invitation))
            throw new AccessDeniedAuthenticationException();

        invitationMatchCrudService.update(dto.toInvitationMatchUpdateDto());
    }

    public void delete(String token, Long id) {
        InvitationMatch invitation = invitationMatchCrudService.find(id);

        if (!checkLeaderOrAdmin(token, invitation))
            throw new AccessDeniedAuthenticationException();

        invitationMatchCrudService.delete(id);
    }

    private boolean checkLeaderOrAdmin(String resolvedToken, InvitationMatch invitation) {
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

    //    public List<InvitationInfoDto> getInvitationInfoByCategoryAndLocationAndDateBetween(String category, String city, String district, LocalDate from, LocalDate to) {
//        List<Invitation> invitations = invitationRepository.findByInvitationTypeAndCityAndDistrictAndStartDateBetweenAndEndDateBetween(
//                Enum.valueOf(InvitationType.class, category)
//                , city
//                , district
//                , LocalDateTime.of(from, LocalTime.MIN)
//                , LocalDateTime.of(to, LocalTime.MAX)
//                , LocalDateTime.of(from, LocalTime.MIN)
//                , LocalDateTime.of(to, LocalTime.MAX));
//
//        return invitations.stream()
//                .map(InvitationInfoDto::new)
//                .collect(Collectors.toList());

//    }
}
