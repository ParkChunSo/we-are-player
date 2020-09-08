package com.chun.apps.match.service;

import com.chun.apps.match.dtos.params.ClubInfoDateParam;
import com.chun.apps.match.dtos.params.ClubInfoParam;
import com.chun.apps.match.dtos.params.ClubsInfoParam;
import com.chun.apps.match.dtos.params.LocationParam;
import com.chun.commons.dtos.match.MatchDetailsResDto;
import com.chun.commons.dtos.match.MatchResDto;
import com.chun.apps.match.dtos.request.MatchSaveReqDto;
import com.chun.apps.match.dtos.request.MatchUpdateReqDto;
import com.chun.apps.match.dtos.request.MatchScoreUpdateReqDto;
import com.chun.commons.enums.MemberRole;
import com.chun.commons.errors.exception.AccessDeniedAuthenticationException;
import com.chun.crud.dtos.ClubInfoDto;
import com.chun.crud.dtos.MatchLocationDto;
import com.chun.crud.dtos.MatchScoreUpdateDto;
import com.chun.crud.dtos.MatchTimeDto;
import com.chun.crud.entity.Match;
import com.chun.crud.member.entitys.Member;
import com.chun.crud.member.service.MemberCrudService;
import com.chun.crud.service.ClubCrudService;
import com.chun.crud.service.MatchCrudService;
import com.chun.crud.util.MatchConvertor;
import com.chun.modules.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final ClubCrudService clubCrudService;
    private final MemberCrudService memberCrudService;
    private final MatchCrudService matchCrudService;
    private final JwtTokenProvider jwtTokenProvider;

    public void save(MatchSaveReqDto dto) {
        matchCrudService.save(dto.toMatchSaveDto());
    }

    //클럽의 경기 기록 조회(전체)
    public List<MatchDetailsResDto> findClubHistory(ClubInfoParam dto) {
        return matchCrudService.findByClub(dto.toClubInfoDto()).stream()
                .map(MatchConvertor::toMatchDetailsResDto)
                .collect(Collectors.toList());
    }

    //클럽의 경기 기록 조회
    public List<MatchResDto> findByClubNameAndClubLocation(ClubInfoDateParam dto) {
        List<Match> matches = matchCrudService.findByClubAndTime(
                ClubInfoDto.builder()
                        .clubName(dto.getClubName())
                        .clubCity(dto.getClubCity())
                        .clubDistrict(dto.getClubDistrict())
                        .build(),
                MatchTimeDto.builder()
                        .from(dto.getFrom())
                        .to(dto.getTo())
                        .build());
        return matches.stream()
                .map(MatchConvertor::toMatchResDto)
                .collect(Collectors.toList());
    }

    // 해당 지역에서 한달 내에 치뤄질 경기 검색
    public List<MatchResDto> findByLocationInMonth(LocationParam dto) {
        List<Match> matches = matchCrudService.findByLocationInMonth(
                MatchLocationDto.builder()
                        .city(dto.getCity())
                        .district(dto.getDistrict())
                        .build());
        return matches.stream()
                .map(MatchConvertor::toMatchResDto)
                .collect(Collectors.toList());
    }

    //두 클럽이 붙었었던 기록(간단히 몇대몇)
    public List<MatchResDto> findTwoClubHistory(ClubsInfoParam dto) {
        List<MatchDetailsResDto> detailsDto = this.findTwoClubDetailsHistory(dto);
        return detailsDto.stream()
                .map(MatchConvertor::toMatchResDto)
                .collect(Collectors.toList());
    }

    //두 클럽이 붙었었던 기록(자세히 누가 골을 넣고 스쿼드가 어땠는지)
    public List<MatchDetailsResDto> findTwoClubDetailsHistory(ClubsInfoParam dto) {
        List<Match> matches = matchCrudService.findByTwoClubs(dto.toClubsInfoDto());
        return matches.stream()
                .map(MatchConvertor::toMatchDetailsResDto)
                .collect(Collectors.toList());
    }

    public void update(String token, MatchUpdateReqDto dto) {
        Match match = matchCrudService.find(dto.getId());

        if(!checkAuthorization(token, match))
            throw new AccessDeniedAuthenticationException();

        matchCrudService.update(dto.toMatchUpdateDto());
    }

    public void updateScore(String token, MatchScoreUpdateReqDto dto) {
        Match match = matchCrudService.find(dto.getId());

        if(!checkAuthorization(token, match))
            throw new AccessDeniedAuthenticationException();

        matchCrudService.updateScore(dto.toMatchScoreUpdateDto());
    }

    public void delete(String token, long id){
        Match match = matchCrudService.find(id);

        if(!checkAuthorization(token, match))
            throw new AccessDeniedAuthenticationException();

        matchCrudService.delete(id);
    }

    private boolean checkAuthorization(String token, Match match){
        ClubInfoParam homeClub = ClubInfoParam.builder()
                .clubName(match.getHomeClub().getClubName())
                .clubCity(match.getHomeClub().getCity())
                .clubDistrict(match.getHomeClub().getDistrict())
                .build();

        ClubInfoParam awayClub = ClubInfoParam.builder()
                .clubName(match.getAwayClub().getClubName())
                .clubCity(match.getAwayClub().getCity())
                .clubDistrict(match.getAwayClub().getDistrict())
                .build();

        // 각 클럽의 리더 혹은 관리자만 접근가능
        return checkLeader(token, homeClub)
                || checkLeader(token, awayClub)
                || isAdmin(token);
    }

    private boolean checkLeader(String token, ClubInfoParam dto) {
        String requestId = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(token));

        List<Member> leaders = clubCrudService.findLeaders(dto.toClubInfoDto());
        return leaders.stream()
                .anyMatch(o -> o.getId().equals(requestId));
    }

    private boolean isAdmin(String token) {
        return jwtTokenProvider.hasRole(jwtTokenProvider.resolveToken(token), MemberRole.ADMIN);
    }

    //TODO("아래는 QueryDsl을 적용 후 작성")
    //가장 많은 승점을 취한 클럽(승점을 적용하는 방법은 EPL기준으로)
//    public List<ClubDetailsInfoDto> findBestClub() {
//        return null;
//    }

    //가장 골을 많이 넣은 플레이어
//    public List<MemberDetailsInfoDto> findTopScorers() {
//        return null;
//    }

    //가장 어시스트를 많이한 플레이어
//    public List<MemberDetailsInfoDto> findTopAssistants() {
//        return null;
//    }
}
