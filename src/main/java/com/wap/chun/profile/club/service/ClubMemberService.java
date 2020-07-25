package com.wap.chun.profile.club.service;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.ClubMember;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.error.exception.AuthorizationException;
import com.wap.chun.error.exception.ClubMemberNotFoundException;
import com.wap.chun.error.exception.ClubNotFoundException;
import com.wap.chun.error.exception.MemberNotFoundException;
import com.wap.chun.profile.club.dtos.ClubLeaderUpdateDto;
import com.wap.chun.profile.club.dtos.ClubMemberDto;
import com.wap.chun.profile.club.dtos.ClubMemberSaveDto;
import com.wap.chun.profile.club.repository.ClubMemberRepository;
import com.wap.chun.profile.club.repository.ClubRepository;
import com.wap.chun.profile.member.repository.MemberRepository;
import com.wap.chun.security.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubMemberService {
    private final ClubMemberRepository clubMemberRepository;
    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public List<ClubMemberDto> getClubMembers(String clubName, String city, String district, ClubMemberType type) {
        List<ClubMember> clubMembers = clubMemberRepository.findByClub_ClubNameAndClub_CityAndClub_DistrictAndClubMemberType(clubName, city, district, type)
                .orElseThrow(ClubMemberNotFoundException::new);

        return clubMembers.stream()
                .map(ClubMemberDto::new)
                .collect(Collectors.toList());
    }

    public void saveClubMember(String token, ClubMemberSaveDto dto) {
        Club club = clubRepository.findByClubNameAndCityAndDistrictAndDeleteFlagFalse(dto.getClubName(), dto.getClubCity(), dto.getClubDistrict())
                .orElseThrow(ClubNotFoundException::new);

        //DataBase에 접근을 줄이기 위해서 따로 쿼리를 날리지 않고 Eager로 받아온 데이터를 활용
        Member leader = club.getClubMembers().stream()
                .filter(clubMember -> clubMember.getClubMemberType().equals(ClubMemberType.LEADER))
                .findFirst()
                .map(ClubMember::getMember)
                .orElseThrow(ClubMemberNotFoundException::new);

        String requestId = jwtTokenProvider.getUsername(token);
        if (!requestId.equals(leader.getId())) {
            throw new AuthorizationException();
        }

        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(MemberNotFoundException::new);

        clubMemberRepository.save(new ClubMember(club, member, dto.getUniformNum(), dto.getPositionType(), dto.getClubMemberType()));
    }


    public void updateClubLeader(ClubLeaderUpdateDto dto) {
        List<ClubMember> clubMembers = clubMemberRepository.findByClub_ClubNameAndClub_CityAndClub_District(dto.getClubName(), dto.getCity(), dto.getDistrict())
                .orElseThrow(ClubMemberNotFoundException::new);
        clubMemberRepository.saveAll(clubMembers.stream()
                .peek(clubMember -> {
                    if (clubMember.getMember().getId().equals(dto.getNewLeaderId())) {
                        clubMember.setClubMemberType(ClubMemberType.LEADER);
                    } else {
                        clubMember.setClubMemberType(ClubMemberType.MEMBER);
                    }
                }).collect(Collectors.toList()));
    }
}
