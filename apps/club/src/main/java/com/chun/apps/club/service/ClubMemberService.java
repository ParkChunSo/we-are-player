package com.chun.apps.club.service;

import com.chun.apps.club.dtos.params.ClubInfoParam;
import com.chun.apps.club.dtos.request.ClubLeaderUpdateReqDto;
import com.chun.apps.club.dtos.request.ClubMemberSaveReqDto;
import com.chun.apps.club.dtos.response.ClubMemberResDto;
import com.chun.commons.enums.ClubMemberType;
import com.chun.commons.errors.exception.AuthorizationException;
import com.chun.crud.dtos.ClubInfoDto;
import com.chun.crud.entitys.ClubMember;
import com.chun.crud.service.ClubCrudService;
import com.chun.crud.service.ClubMemberCrudService;
import com.chun.modules.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubMemberService {
    private final ClubCrudService clubCrudService;
    private final ClubMemberCrudService clubMemberCrudService;
    private final JwtTokenProvider jwtTokenProvider;

    public void saveClubMember(String token, ClubMemberSaveReqDto dto) {
        String requestId = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(token));

        ClubMember clubLeaders = clubMemberCrudService.find(
                ClubInfoDto.builder()
                        .clubName(dto.getClubName())
                        .clubCity(dto.getClubCity())
                        .clubDistrict(dto.getClubDistrict())
                        .build(), requestId);

        if(!clubLeaders.getClubMemberType().equals(ClubMemberType.LEADER)){
            throw new AuthorizationException();
        }

        clubMemberCrudService.save(dto.toClubMemberSaveDto());
    }

    public List<ClubMemberResDto> findByClub(ClubInfoParam dto, ClubMemberType type) {
        List<ClubMember> clubMembers = clubMemberCrudService.findClubMembers(dto.toClubInfoDto(), type);

        return clubMembers.stream()
                .map(ClubMemberResDto::new)
                .collect(Collectors.toList());
    }


    public void updateClubLeader(ClubLeaderUpdateReqDto dto) {
        clubMemberCrudService.updateLeader(dto.toClubLeaderUpdateDto());
    }
}
