package com.wap.api.profile.club.service;

import com.wap.api.domain.entitys.Club;
import com.wap.api.domain.entitys.ClubMember;
import com.wap.api.domain.entitys.Member;
import com.wap.api.domain.enums.ClubMemberType;
import com.wap.api.error.exception.AuthorizationException;
import com.wap.api.error.exception.ClubMemberNotFoundException;
import com.wap.api.error.exception.ClubNotFoundException;
import com.wap.api.error.exception.MemberNotFoundException;
import com.wap.api.profile.club.dtos.params.ClubInfoParam;
import com.wap.api.profile.club.dtos.request.ClubLeaderUpdateDto;
import com.wap.api.profile.club.dtos.response.ClubMemberDto;
import com.wap.api.profile.club.dtos.request.ClubMemberSaveDto;
import com.wap.api.profile.club.repository.ClubMemberRepository;
import com.wap.api.profile.club.repository.ClubRepository;
import com.wap.api.profile.member.repository.MemberRepository;
import com.wap.api.security.util.JwtTokenProvider;
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


    public List<ClubMemberDto> getClubMembers(ClubInfoParam dto, ClubMemberType type) {
        List<ClubMember> clubMembers = clubMemberRepository.findByClub_ClubNameAndClub_CityAndClub_DistrictAndClubMemberType(dto.getName(), dto.getCity(), dto.getDistrict(), type)
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

        String requestId = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(token));
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
