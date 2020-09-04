package com.chun.crud.service;

import com.chun.commons.enums.ClubMemberType;
import com.chun.commons.errors.exception.ClubAlreadyExistException;
import com.chun.commons.errors.exception.ClubNotFoundException;
import com.chun.commons.errors.exception.MemberNotFoundException;
import com.chun.crud.dtos.*;
import com.chun.crud.entitys.Club;
import com.chun.crud.entitys.ClubMember;
import com.chun.crud.member.entitys.Member;
import com.chun.crud.member.repository.MemberCrudRepository;
import com.chun.crud.repository.ClubCrudRepository;
import com.chun.crud.repository.ClubMemberCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
