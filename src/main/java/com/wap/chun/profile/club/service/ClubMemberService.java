package com.wap.chun.profile.club.service;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.ClubMember;
import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.error.exception.AuthorizationException;
import com.wap.chun.error.exception.ClubMemberNotFoundException;
import com.wap.chun.profile.club.dtos.ClubMemberDto;
import com.wap.chun.profile.club.dtos.ClubMemberSaveDto;
import com.wap.chun.profile.club.repository.ClubMemberRepository;
import com.wap.chun.security.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubMemberService {
    private final ClubMemberRepository clubMemberRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public List<ClubMemberDto> getClubMembers(String clubName, String clubLocation, ClubMemberType type){
        List<ClubMember> clubMembers = clubMemberRepository.findByClub_ClubNameAndClub_LocationAndClubMemberType(clubName, clubLocation, type)
                .orElseThrow(ClubMemberNotFoundException::new);

        return clubMembers.stream()
                .map(ClubMemberDto::new)
                .collect(Collectors.toList());
    }

    public void saveClubMember(String token, ClubMemberSaveDto dto){
        ClubMember clubMembers = clubMemberRepository
                .findFirstByClub_ClubNameAndClub_LocationAndClubMemberType(dto.getClubName(), dto.getClubLocation(), ClubMemberType.MEMBER)
                .orElseThrow(ClubMemberNotFoundException::new);

        Club club = clubMembers.getClub();

        String requestId = jwtTokenProvider.getUsername(token);
        if(!requestId.equals(club.getLeader().getId()))
            throw new AuthorizationException();

//        clubMemberRepository.save()
    }
}
