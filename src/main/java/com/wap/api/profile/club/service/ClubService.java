package com.wap.api.profile.club.service;

import com.wap.api.profile.domain.entitys.Club;
import com.wap.api.profile.domain.entitys.ClubMember;
import com.wap.api.profile.domain.entitys.Member;
import com.wap.api.error.exception.ClubAlreadyExistException;
import com.wap.api.error.exception.ClubNotFoundException;
import com.wap.api.error.exception.MemberNotFoundException;
import com.wap.api.profile.club.dtos.ClubInfoDto;
import com.wap.api.profile.club.dtos.ClubInfoUpdateDto;
import com.wap.api.profile.club.dtos.ClubMemberDto;
import com.wap.api.profile.club.repository.ClubMemberRepository;
import com.wap.api.profile.club.repository.ClubRepository;
import com.wap.api.profile.member.repository.MemberRepository;
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

    public void createClub(ClubInfoDto dto) {
        if (clubRepository.existsByClubNameAndCityAndDistrict(dto.getClubName(), dto.getCity(), dto.getDistrict())) {
            throw new ClubAlreadyExistException();
        }
        Club club = clubRepository.save(new Club(dto));
        List<Member> members = memberRepository.findAllById(
                dto.getMembers().stream()
                        .map(ClubMemberDto::getMemberId)
                        .collect(Collectors.toList()));

        List<ClubMember> clubMembers = members.stream()
                .map(member -> {
                    ClubMember clubMember = null;
                    for (ClubMemberDto clubMemberDto : dto.getMembers()) {
                        if (clubMemberDto.getMemberId().equals(member.getId())) {
                            clubMember = ClubMember.builder()
                                    .club(club)
                                    .clubMemberType(clubMemberDto.getType())
                                    .member(member)
                                    .positionType(clubMemberDto.getPosition())
                                    .uniformNum(clubMemberDto.getUniformNum())
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

    public ClubInfoDto getClubInfo(String clubName, String clubCity, String clubDistrict) {
        Club club = clubRepository.findByClubNameAndCityAndDistrictAndDeleteFlagFalse(clubName, clubCity, clubDistrict)
                .orElseThrow(ClubNotFoundException::new);
        return new ClubInfoDto(club);
    }

    public List<ClubInfoDto> findByClubName(String clubName) {
        List<Club> clubList = clubRepository.findByClubName(clubName)
                .orElseThrow(ClubNotFoundException::new);

        return clubList.stream()
                .map(ClubInfoDto::new)
                .collect(Collectors.toList());
    }

    public List<ClubInfoDto> findByLocation(String clubCity, String clubDistrict) {
        List<Club> clubList = clubRepository.findByCityAndDistrict(clubCity, clubDistrict)
                .orElseThrow(ClubNotFoundException::new);

        return clubList.stream()
                .map(ClubInfoDto::new)
                .collect(Collectors.toList());
    }

    public void updateClubLogoUri(ClubInfoUpdateDto dto) {
        Club club = clubRepository.findByClubNameAndCityAndDistrictAndDeleteFlagFalse(dto.getClubName(), dto.getCity(),dto.getDistrict())
                .orElseThrow(ClubNotFoundException::new);
        club.setLogoUri(dto.getLogoUri());
        clubRepository.save(club);
    }

    public void updateLikeAndRudeCnt(ClubInfoUpdateDto dto) {
        Club club = clubRepository.findByClubNameAndCityAndDistrictAndDeleteFlagFalse(dto.getClubName(), dto.getCity(),dto.getDistrict())
                .orElseThrow(ClubNotFoundException::new);
        club.setLikeCnt(dto.getLikeCnt());
        club.setRudeCnt(dto.getRudeCnt());
        clubRepository.save(club);
    }

    public void deleteClub(String clubName, String clubCity, String clubDistrict) {
        Club club = clubRepository.findByClubNameAndCityAndDistrictAndDeleteFlagFalse(clubName, clubCity, clubDistrict)
                .orElseThrow(ClubNotFoundException::new);
        club.deleteClub();
        clubRepository.save(club);
    }
}
