package com.chun.crud.service;

import com.chun.commons.enums.ClubMemberType;
import com.chun.commons.errors.exception.ClubMemberNotFoundException;
import com.chun.commons.errors.exception.ClubNotFoundException;
import com.chun.commons.errors.exception.MemberNotFoundException;
import com.chun.crud.dtos.*;
import com.chun.crud.entitys.Club;
import com.chun.crud.entitys.ClubMember;
import com.chun.crud.entitys.Member;
import com.chun.crud.entitys.MemberRepository;
import com.chun.crud.repository.ClubCrudRepository;
import com.chun.crud.repository.ClubMemberCrudRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ClubMemberCrudService {
    private final ClubCrudRepository clubCrudRepository;
    private final ClubMemberCrudRepository clubMemberCrudRepository;
    private final MemberRepository memberRepository;

    public ClubMember save(ClubMemberCreateDto dto) {
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getClubCity(), dto.getClubDistrict())
                .orElseThrow(ClubNotFoundException::new);

        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(MemberNotFoundException::new);

        return clubMemberCrudRepository.save(
                ClubMember.builder()
                        .club(club)
                        .member(member)
                        .uniformNum(dto.getUniformNum())
                        .positionType(dto.getPositionType())
                        .clubMemberType(dto.getClubMemberType())
                        .build());
    }

    public List<ClubMember> findClubMembers(ClubInfoDto dto, ClubMemberType type) {
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getName(), dto.getCity(), dto.getDistrict())
                .orElseThrow(ClubNotFoundException::new);

        return clubMemberCrudRepository.findByClubAndClubMemberType(club, type)
                .orElseThrow(ClubMemberNotFoundException::new);
    }

    public ClubMember updateLeader(ClubLeaderUpdateDto dto) {
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getCity(), dto.getDistrict())
                .orElseThrow(ClubNotFoundException::new);

        Member preLeader = memberRepository.findById(dto.getPreLeaderId())
                .orElseThrow(MemberNotFoundException::new);

        Member newLeader = memberRepository.findById(dto.getNewLeaderId())
                .orElseThrow(MemberNotFoundException::new);

        ClubMember clubMember = clubMemberCrudRepository.findByClubAndMemberAndClubMemberType(club, preLeader, ClubMemberType.LEADER)
                .orElseThrow(ClubMemberNotFoundException::new);

        return clubMemberCrudRepository.save(clubMember.updateLeader(newLeader));
    }

    public ClubMember updateInfo(ClubMemberUpdateDto dto) {
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getCity(), dto.getDistrict())
                .orElseThrow(ClubNotFoundException::new);

        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(MemberNotFoundException::new);

        ClubMember clubMember = clubMemberCrudRepository.findByClubAndMemberAndClubMemberType(club, member, ClubMemberType.LEADER)
                .orElseThrow(ClubMemberNotFoundException::new);

        return clubMemberCrudRepository.save(clubMember.updateInfo(dto.getUniformNum(), dto.getPositionType()));
    }

    public void delete(ClubMemberDeleteDto dto){
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getClubCity(), dto.getClubDistrict())
                .orElseThrow(ClubNotFoundException::new);

        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(MemberNotFoundException::new);

        clubMemberCrudRepository.deleteAllByClubAndMember(club, member);
    }
}
