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
    private final ClubMemberRepository clubMemberRepository;

    public void createClub(ClubInfoDto dto){
        if(clubRepository.existsByClubNameAndLocation(dto.getClubName(), dto.getLocation())) {
            throw new ClubAlreadyExistException();
        }
        Club save = clubRepository.save(new Club(dto));
        List<Member> member = memberRepository.findAllById(dto.getMembers().stream()
                .map(ClubMemberDto::getMemberId)
                .collect(Collectors.toList()));

        List<ClubMember> clubMembers = member.stream()
                .map(m -> {
                    ClubMember clubMember = null;
                    for (ClubMemberDto clubMemberDto : dto.getMembers()) {
                        if (clubMemberDto.getMemberId().equals(m.getId())) {
                            clubMember = ClubMember.builder()
                                    .club(save)
                                    .clubMemberType(clubMemberDto.getType())
                                    .member(m)
                                    .positionType(clubMemberDto.getPosition())
                                    .uniformNum(0)
                                    .build();
                            break;
                        }
                    }
                    if (clubMember == null)
                        throw new MemberNotFoundException();

                    return clubMember;
                }).collect(Collectors.toList());

        clubMemberRepository.saveAll(clubMembers);
    }

    public ClubInfoDto getClubInfo(String clubName, String clubLocation){
        Club club = clubRepository.findByClubNameAndLocationAndDeleteFlagFalse(clubName, clubLocation)
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

    public void updateClubLogoUri(ClubInfoUpdateDto dto){
        Club club = clubRepository.findByClubNameAndLocationAndDeleteFlagFalse(dto.getClubName(), dto.getLocation())
                .orElseThrow(ClubNotFoundException::new);
        club.setLogoUri(dto.getLogoUri());
        clubRepository.save(club);
    }

    public void updateLikeAndRudeCnt(ClubInfoUpdateDto dto){
        Club club = clubRepository.findByClubNameAndLocationAndDeleteFlagFalse(dto.getClubName(), dto.getLocation())
                .orElseThrow(ClubNotFoundException::new);
        club.setLikeCnt(dto.getLikeCnt());
        club.setRudeCnt(dto.getRudeCnt());
        clubRepository.save(club);
    }

    public void deleteClub(String clubName, String clubLocation){
        Club club = clubRepository.findByClubNameAndLocationAndDeleteFlagFalse(clubName, clubLocation)
                .orElseThrow(ClubNotFoundException::new);
        club.deleteClub();
        clubRepository.save(club);
    }
}
