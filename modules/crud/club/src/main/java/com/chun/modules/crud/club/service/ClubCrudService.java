package com.chun.modules.crud.club.service;

import com.chun.commons.enums.ClubMemberType;
import com.chun.commons.errors.exception.ClubAlreadyExistException;
import com.chun.commons.errors.exception.ClubNotFoundException;
import com.chun.commons.errors.exception.MemberNotFoundException;
import com.chun.modules.crud.club.dtos.*;
import com.chun.modules.crud.club.entitys.Club;
import com.chun.modules.crud.club.entitys.ClubMember;
import com.chun.modules.crud.member.entitys.Member;
import com.chun.modules.crud.member.repository.MemberCrudRepository;
import com.chun.modules.crud.club.repository.ClubCrudRepository;
import com.chun.modules.crud.club.repository.ClubMemberCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubCrudService {
    private final ClubCrudRepository clubCrudRepository;
    private final ClubMemberCrudRepository clubMemberCrudRepository;
    private final MemberCrudRepository memberCrudRepository;

    public Club save(ClubSaveDto dto){
        if(clubCrudRepository.existsByClubNameAndCityAndDistrict(
                dto.getClubName(), dto.getClubCity(), dto.getClubDistrict()))
            throw new ClubAlreadyExistException();

        Member leader = memberCrudRepository.findById(dto.getLeader().getMemberId())
                .orElseThrow(MemberNotFoundException::new);

        Club club = clubCrudRepository.save(Club.builder()
                .city(dto.getClubCity())
                .district(dto.getClubDistrict())
                .clubName(dto.getClubName())
                .logoUri(dto.getLogoUri())
                .build());

        clubMemberCrudRepository.save(ClubMember.builder()
                .club(club)
                .member(leader)
                .clubMemberType(ClubMemberType.LEADER)
                .positionType(dto.getLeader().getPositionType())
                .uniformNum(dto.getLeader().getUniformNum())
                .build());

        return club;
    }

    public Club find (long id){
        return clubCrudRepository.findById(id)
                .orElseThrow(ClubNotFoundException::new);
    }

    public Club find(ClubInfoDto dto){
        return clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getClubCity(), dto.getClubDistrict())
                .orElseThrow(ClubNotFoundException::new);
    }

    public List<Club> findClubByName(String clubName){
        return clubCrudRepository.findByClubName(clubName)
                .orElseThrow(ClubNotFoundException::new);
    }

    public List<Club> findByLocation(LocationDto dto){
        return clubCrudRepository.findByCityAndDistrict(dto.getCity(), dto.getDistrict())
                .orElseThrow(ClubNotFoundException::new);
    }

    public List<Member> findLeaders(long id){
        Club club = find(id);
        List<ClubMember> clubLeaders = clubMemberCrudRepository.findByClubAndClubMemberType(club, ClubMemberType.LEADER)
                .orElse(new ArrayList<>());
        return clubLeaders.stream()
                .map(ClubMember::getMember)
                .collect(Collectors.toList());
    }

    public List<Member> findLeaders(ClubInfoDto dto){
        Club club = find(dto);
        List<ClubMember> clubLeaders = clubMemberCrudRepository.findByClubAndClubMemberType(club, ClubMemberType.LEADER)
                .orElse(new ArrayList<>());
        return clubLeaders.stream()
                .map(ClubMember::getMember)
                .collect(Collectors.toList());
    }

    public Club update(ClubUpdateDto dto){
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getCity(), dto.getDistrict())
                .orElseThrow(ClubNotFoundException::new);
        club.updateInfo(dto.getLogoUri(), dto.getLikeCnt(), dto.getRudeCnt(), dto.getPoint());
        return clubCrudRepository.save(club);
    }

    public void delete(ClubDeleteDto dto){
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getClubCity(), dto.getClubDistrict())
                .orElseThrow(ClubNotFoundException::new);
        clubCrudRepository.delete(club);
    }
}
