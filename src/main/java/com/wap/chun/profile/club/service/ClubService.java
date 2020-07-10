package com.wap.chun.profile.club.service;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.ClubMember;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.error.ClubAlreadyExistException;
import com.wap.chun.error.ClubCannotFindException;
import com.wap.chun.error.ClubMemberCannotFindException;
import com.wap.chun.error.MemberCannotFindException;
import com.wap.chun.profile.club.dtos.ClubInfoDto;
import com.wap.chun.profile.club.dtos.ClubInfoUpdateDto;
import com.wap.chun.profile.club.dtos.ClubLeaderUpdateDto;
import com.wap.chun.profile.club.dtos.ClubMemberDto;
import com.wap.chun.profile.club.repository.ClubMemberRepository;
import com.wap.chun.profile.club.repository.ClubRepository;
import com.wap.chun.profile.member.repository.MemberRepository;
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

        Member member = memberRepository.findById(dto.getLeaderId())
                .orElseThrow(MemberCannotFindException::new);

        clubRepository.save(new Club(dto, member));
    }

    public ClubInfoDto getClubInfo(String clubName, String clubLocation){
        Club club = clubRepository.findByClubNameAndLocation(clubName, clubLocation)
                .orElseThrow(ClubCannotFindException::new);
        return new ClubInfoDto(club);
    }

    public List<ClubInfoDto> findByClubName(String clubName){
        List<Club> clubList = clubRepository.findByClubName(clubName)
                .orElseThrow(ClubCannotFindException::new);

        return clubList.stream()
                .map(ClubInfoDto::new)
                .collect(Collectors.toList());
    }

    public List<ClubInfoDto> findByLocation(String clubLocation){
        List<Club> clubList = clubRepository.findByLocation(clubLocation)
                .orElseThrow(ClubCannotFindException::new);

        return clubList.stream()
                .map(ClubInfoDto::new)
                .collect(Collectors.toList());
    }

    public void updateClubLeader(ClubLeaderUpdateDto dto){
        Club club = clubRepository.findByClubNameAndLocation(dto.getClubName(), dto.getLocation())
                .orElseThrow(ClubCannotFindException::new);

        club.setLeader(memberRepository.findById(dto.getNewLeaderId())
                    .orElseThrow(MemberCannotFindException::new));

        clubRepository.save(club);
    }

    public void updateClubLogoUri(ClubInfoUpdateDto dto){
        Club club = clubRepository.findByClubNameAndLocation(dto.getClubName(), dto.getLocation())
                .orElseThrow(ClubCannotFindException::new);
        club.setLogoUri(dto.getLogoUri());
        clubRepository.save(club);
    }

    public void updateLikeAndRudeCnt(ClubInfoUpdateDto dto){
        Club club = clubRepository.findByClubNameAndLocation(dto.getClubName(), dto.getLocation())
                .orElseThrow(ClubCannotFindException::new);
        club.setLikeCnt(dto.getLikeCnt());
        club.setRudeCnt(dto.getRudeCnt());
        clubRepository.save(club);
    }

    public List<ClubMemberDto> getClubMembers(String clubName, String clubLocation, ClubMemberType type){
        Club club = clubRepository.findByClubNameAndLocation(clubName, clubLocation)
                .orElseThrow(ClubCannotFindException::new);

        List<ClubMember> memberList = clubMemberRepository.findByClubAndClubMemberType(club, type)
                .orElseThrow(ClubMemberCannotFindException::new);

        return memberList.stream()
                .map(ClubMemberDto::new)
                .collect(Collectors.toList());
    }

    public void deleteClub(String clubName, String clubLocation){
        clubRepository.deleteByClubNameAndLocation(clubName, clubLocation);
    }
}
