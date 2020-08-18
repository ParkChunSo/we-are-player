package com.wap.api.post.invitation.service;

import com.wap.api.profile.domain.entitys.Club;
import com.wap.api.profile.domain.entitys.ClubMember;
import com.wap.api.profile.domain.entitys.Invitation;
import com.wap.api.profile.domain.entitys.Member;
import com.wap.api.profile.domain.enums.ClubMemberType;
import com.wap.api.profile.domain.enums.InvitationType;
import com.wap.api.profile.domain.enums.MemberRole;
import com.wap.api.error.exception.AccessDeniedAuthenticationException;
import com.wap.api.error.exception.ClubNotFoundException;
import com.wap.api.error.exception.InvitationNotFoundException;
import com.wap.api.error.exception.MemberNotFoundException;
import com.wap.api.post.invitation.dto.InvitationInfoDto;
import com.wap.api.post.invitation.dto.InvitationSaveDto;
import com.wap.api.post.invitation.repository.InvitationRepository;
import com.wap.api.post.submit.repository.SubmitMatchRepository;
import com.wap.api.post.submit.repository.SubmitMemberRepository;
import com.wap.api.profile.club.repository.ClubRepository;
import com.wap.api.profile.member.repository.MemberRepository;
import com.wap.api.security.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvitationService {
    private final InvitationRepository invitationRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;
    private final SubmitMemberRepository submitMemberRepository;
    private final SubmitMatchRepository submitMatchRepository;

    public List<InvitationInfoDto> getAll() {
        return invitationRepository.findAll().stream()
                .map(InvitationInfoDto::new)
                .collect(Collectors.toList());
    }

    public InvitationInfoDto getInvitationInfoById(Long id) {
        Invitation invitation = invitationRepository.findById(id)
                .orElseThrow(InvitationNotFoundException::new);

        return new InvitationInfoDto(invitation);
    }

    public List<InvitationInfoDto> getInvitationInfoByCategoryAndLocation(String category, String city, String district) {
        List<Invitation> invitations = invitationRepository.findByCategoryAndCityAndDistrictAndEndDateAfter(Enum.valueOf(InvitationType.class, category), city, district, LocalDateTime.now());
        return invitations.stream()
                .map(InvitationInfoDto::new)
                .collect(Collectors.toList());
    }

    public List<InvitationInfoDto> getInvitationInfoByCategoryAndLocationAndDateBetween(String category, String city, String district, LocalDate from, LocalDate to) {
        List<Invitation> invitations = invitationRepository.findByCategoryAndCityAndDistrictAndStartDateBetweenAndEndDateBetween(
                Enum.valueOf(InvitationType.class, category)
                , city
                , district
                , LocalDateTime.of(from, LocalTime.MIN)
                , LocalDateTime.of(to, LocalTime.MAX)
                , LocalDateTime.of(from, LocalTime.MIN)
                , LocalDateTime.of(to, LocalTime.MAX));

        return invitations.stream()
                .map(InvitationInfoDto::new)
                .collect(Collectors.toList());
    }

    public void saveInvitation(InvitationSaveDto dto) {
        Member writer = memberRepository.findById(dto.getWriterId())
                .orElseThrow(MemberNotFoundException::new);
        Club club = clubRepository.findByClubNameAndCityAndDistrictAndDeleteFlagFalse(dto.getClubName(), dto.getClubCity(), dto.getClubDistrict())
                .orElseThrow(ClubNotFoundException::new);

        invitationRepository.save(new Invitation(dto, club, writer));
    }

    public void updateInvitation(String token, InvitationInfoDto dto) {
        Invitation invitation = invitationRepository.findById(dto.getId())
                .orElseThrow(InvitationNotFoundException::new);

        if (!checkSelfOrLeaderOrAdmin(token, invitation))
            throw new AccessDeniedAuthenticationException();

        invitation = invitation.updateInfo(dto.getWantedCity(), dto.getWantedDistrict(), dto.getEndDate(), dto.getMessage());
        invitationRepository.save(invitation);
    }

    public void deleteInvitation(String token, Long id) {
        Invitation invitation = invitationRepository.findById(id)
                .orElseThrow(InvitationNotFoundException::new);

        if (!checkSelfOrLeaderOrAdmin(token, invitation))
            throw new AccessDeniedAuthenticationException();

        invitationRepository.deleteById(id);
    }

    private boolean checkSelfOrLeaderOrAdmin(String token, Invitation invitation) {
        String requestId = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(token));

        boolean isLeader = invitation.getClub().getClubMembers().stream()
                .filter(clubMember -> clubMember.getClubMemberType().equals(ClubMemberType.LEADER))
                .map(ClubMember::getMember)
                .anyMatch(member -> member.getId().equals(requestId));

        return requestId.equals(invitation.getWriter().getId())
                || isLeader
                || jwtTokenProvider.hasRole(jwtTokenProvider.resolveToken(token), MemberRole.ADMIN);
    }
}
