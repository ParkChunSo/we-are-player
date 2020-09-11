package com.chun.apps.invitation.service;

import com.chun.apps.invitation.dtos.request.SubmitMatchSaveReqDto;
import com.chun.apps.invitation.dtos.request.SubmitMatchUpdateReqDto;
import com.chun.commons.dtos.invitation.SubmitMatchResDto;
import com.chun.commons.enums.ClubMemberType;
import com.chun.commons.enums.MemberRole;
import com.chun.commons.errors.exception.AuthorizationException;
import com.chun.crud.dtos.ClubInfoDto;
import com.chun.crud.entitys.Club;
import com.chun.crud.entitys.ClubMember;
import com.chun.crud.invitation.match.entity.InvitationMatch;
import com.chun.crud.invitation.match.entity.SubmitMatch;
import com.chun.crud.invitation.match.service.InvitationMatchCrudService;
import com.chun.crud.invitation.match.service.SubmitMatchCrudService;
import com.chun.crud.invitation.match.util.InvitationMatchConvertor;
import com.chun.crud.member.entitys.Member;
import com.chun.crud.service.ClubCrudService;
import com.chun.modules.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmitMatchService {
    private final JwtTokenProvider jwtTokenProvider;
    private final ClubCrudService clubCrudService;
    private final InvitationMatchCrudService invitationMatchCrudService;
    private final SubmitMatchCrudService submitMatchCrudService;


    public SubmitMatchResDto find(Long id){
        SubmitMatch submit = submitMatchCrudService.find(id);
        return InvitationMatchConvertor.toSubmitMatchResDto(submit);
    }
    public void save(String token, SubmitMatchSaveReqDto dto){
        ClubInfoDto clubInfoDto = ClubInfoDto.builder()
                .clubName(dto.getClubName())
                .clubDistrict(dto.getClubDistrict())
                .clubCity(dto.getClubCity())
                .build();

        if(!checkLeaderOrAdmin(jwtTokenProvider.resolveToken(token), clubInfoDto)){
            throw new AuthorizationException();
        }

        submitMatchCrudService.save(dto.toSubmitMatchSaveDto());
    }

    @Transactional
    public void update(String token, SubmitMatchUpdateReqDto dto) {
        InvitationMatch invitation = invitationMatchCrudService.find(dto.getInvitationId());

        if(!isLeader(jwtTokenProvider.resolveToken(token), invitation.getClub())){
            throw new AuthorizationException();
        }

        submitMatchCrudService.update(dto.toSubmitMatchUpdateDto());
    }

    public void delete(String token, Long id){
        SubmitMatch submit = submitMatchCrudService.find(id);

        if(!isLeader(jwtTokenProvider.resolveToken(token), submit.getClub())){
            throw new AuthorizationException();
        }

        submitMatchCrudService.delete(id);
    }

    private boolean checkLeaderOrAdmin(String resolvedToken, ClubInfoDto dto) {
        String requestId = jwtTokenProvider.getUsername(resolvedToken);
        List<Member> leaders = clubCrudService.findLeaders(dto);

        return  isLeader(requestId, leaders)
                || isAdmin(resolvedToken);
    }

    private boolean isAdmin(String token) {
        return jwtTokenProvider.hasRole(jwtTokenProvider.resolveToken(token), MemberRole.ADMIN);
    }

    private boolean isLeader(String requestId, List<Member> leaders){
        return leaders.stream()
                .anyMatch(leader -> leader.getId().equals(requestId));
    }

    private boolean isLeader(String requestId, Club club){
        return club.getClubMembers().stream()
                .filter(clubMember -> clubMember.getClubMemberType().equals(ClubMemberType.LEADER))
                .map(ClubMember::getMember)
                .anyMatch(member -> member.getId().equals(requestId));
    }
}
