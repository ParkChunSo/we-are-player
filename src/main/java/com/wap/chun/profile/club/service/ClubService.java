package com.wap.chun.profile.club.service;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.ClubMember;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.error.exception.*;
import com.wap.chun.profile.club.dtos.ClubInfoDto;
import com.wap.chun.profile.club.dtos.ClubInfoUpdateDto;
import com.wap.chun.profile.club.dtos.ClubLeaderUpdateDto;
import com.wap.chun.profile.club.dtos.ClubMemberDto;
import com.wap.chun.profile.club.repository.ClubMemberRepository;
import com.wap.chun.profile.club.repository.ClubRepository;
import com.wap.chun.profile.member.repository.MemberRepository;
import com.wap.chun.security.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//TODO("AWS S3 추가하며 로고 저장하는 로직 구현")

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;

    public void createClub(ClubInfoDto dto){
        if(clubRepository.existsByClubNameAndLocation(dto.getClubName(), dto.getLocation())) {
            throw new ClubAlreadyExistException();
        }

        Member member = memberRepository.findById(dto.getLeaderId())
                .orElseThrow(MemberNotFoundException::new);

        clubRepository.save(new Club(dto, member));
    }

    public ClubInfoDto getClubInfo(String clubName, String clubLocation){
        Club club = clubRepository.findByClubNameAndLocation(clubName, clubLocation)
                .orElseThrow(ClubNotFoundException::new);
        return new ClubInfoDto(club);
    }

    public List<ClubInfoDto> findByClubName(String clubName){
        List<Club> clubList = clubRepository.findByClubName(clubName)
                .orElseThrow(ClubNotFoundException::new);

        return clubList.stream()
                .map(ClubInfoDto::new)
                .collect(Collectors.toList());
    }

    public List<ClubInfoDto> findByLocation(String clubLocation){
        List<Club> clubList = clubRepository.findByLocation(clubLocation)
                .orElseThrow(ClubNotFoundException::new);

        return clubList.stream()
                .map(ClubInfoDto::new)
                .collect(Collectors.toList());
    }

    public void updateClubLeader(ClubLeaderUpdateDto dto){
        Club club = clubRepository.findByClubNameAndLocation(dto.getClubName(), dto.getLocation())
                .orElseThrow(ClubNotFoundException::new);

        club.setLeader(memberRepository.findById(dto.getNewLeaderId())
                    .orElseThrow(MemberNotFoundException::new));

        clubRepository.save(club);
    }

    public void updateClubLogoUri(ClubInfoUpdateDto dto){
        Club club = clubRepository.findByClubNameAndLocation(dto.getClubName(), dto.getLocation())
                .orElseThrow(ClubNotFoundException::new);
        club.setLogoUri(dto.getLogoUri());
        clubRepository.save(club);
    }

    public void updateLikeAndRudeCnt(ClubInfoUpdateDto dto){
        Club club = clubRepository.findByClubNameAndLocation(dto.getClubName(), dto.getLocation())
                .orElseThrow(ClubNotFoundException::new);
        club.setLikeCnt(dto.getLikeCnt());
        club.setRudeCnt(dto.getRudeCnt());
        clubRepository.save(club);
    }

    public void deleteClub(String clubName, String clubLocation){
        clubRepository.deleteByClubNameAndLocation(clubName, clubLocation);
    }
}
