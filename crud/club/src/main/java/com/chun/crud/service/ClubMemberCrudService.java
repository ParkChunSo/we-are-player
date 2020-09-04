package com.chun.crud.service;

import com.chun.commons.enums.ClubMemberType;
import com.chun.commons.errors.exception.ClubMemberNotFoundException;
import com.chun.commons.errors.exception.ClubNotFoundException;
import com.chun.commons.errors.exception.MemberNotFoundException;
import com.chun.crud.dtos.ClubMemberDeleteDto;
import com.chun.crud.dtos.ClubMemberSaveDto;
import com.chun.crud.dtos.ClubMemberUpdateDto;
import com.chun.crud.entitys.ClubMember;
import com.chun.crud.repository.ClubMemberCrudRepository;
import com.chun.crud.dtos.*;
import com.chun.crud.entitys.Club;
import com.chun.crud.member.entitys.Member;
import com.chun.crud.member.repository.MemberCrudRepository;
import com.chun.crud.repository.ClubCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubMemberCrudService {
    private final ClubCrudRepository clubCrudRepository;
    private final ClubMemberCrudRepository clubMemberCrudRepository;
    private final MemberCrudRepository memberCrudRepository;

    public ClubMember save(ClubMemberSaveDto dto) {
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getClubCity(), dto.getClubDistrict())
                .orElseThrow(ClubNotFoundException::new);

        Member member = memberCrudRepository.findById(dto.getMemberId())
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

    public ClubMember find(ClubInfoDto dto, String memberId){
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getClubCity(), dto.getClubDistrict())
                .orElseThrow(ClubNotFoundException::new);
        Member member = memberCrudRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);

        return clubMemberCrudRepository.findByClubAndMember(club, member)
                .orElseThrow(ClubMemberNotFoundException::new);
    }

    public List<ClubMember> findClubMembers(ClubInfoDto dto, ClubMemberType type) {
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getClubCity(), dto.getClubDistrict())
                .orElseThrow(ClubNotFoundException::new);

        return clubMemberCrudRepository.findByClubAndClubMemberType(club, type)
                .orElseThrow(ClubMemberNotFoundException::new);
    }

    @Transactional
    public void updateLeader(ClubLeaderUpdateDto dto) {
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getCity(), dto.getDistrict())
                .orElseThrow(ClubNotFoundException::new);

        Member preLeader = memberCrudRepository.findById(dto.getPreLeaderId())
                .orElseThrow(MemberNotFoundException::new);
        Member newLeader = memberCrudRepository.findById(dto.getNewLeaderId())
                .orElseThrow(MemberNotFoundException::new);

        ClubMember preLeaderInfo = clubMemberCrudRepository.findByClubAndMember(club, preLeader)
                .orElseThrow(ClubMemberNotFoundException::new);
        ClubMember newLeaderInfo = clubMemberCrudRepository.findByClubAndMember(club, newLeader)
                .orElseThrow(ClubMemberNotFoundException::new);

        clubMemberCrudRepository.save(preLeaderInfo.updateClubMemberType(ClubMemberType.MEMBER));
        clubMemberCrudRepository.save(newLeaderInfo.updateClubMemberType(ClubMemberType.LEADER));
    }

    public ClubMember updateInfo(ClubMemberUpdateDto dto) {
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getCity(), dto.getDistrict())
                .orElseThrow(ClubNotFoundException::new);

        Member member = memberCrudRepository.findById(dto.getMemberId())
                .orElseThrow(MemberNotFoundException::new);

        ClubMember clubMember = clubMemberCrudRepository.findByClubAndMember(club, member)
                .orElseThrow(ClubMemberNotFoundException::new);

        return clubMemberCrudRepository.save(clubMember.updateInfo(dto.getUniformNum(), dto.getPositionType()));
    }

    public void delete(ClubMemberDeleteDto dto){
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getClubCity(), dto.getClubDistrict())
                .orElseThrow(ClubNotFoundException::new);

        Member member = memberCrudRepository.findById(dto.getMemberId())
                .orElseThrow(MemberNotFoundException::new);

        clubMemberCrudRepository.deleteAllByClubAndMember(club, member);
    }
}
