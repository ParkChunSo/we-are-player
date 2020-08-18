package com.wap.chun.match.service;

import com.wap.chun.domain.entitys.*;
import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.domain.enums.MatchState;
import com.wap.chun.domain.enums.MatchType;
import com.wap.chun.domain.enums.MemberRole;
import com.wap.chun.error.exception.AccessDeniedAuthenticationException;
import com.wap.chun.error.exception.ClubNotFoundException;
import com.wap.chun.error.exception.InvalidInputValueException;
import com.wap.chun.error.exception.MatchNotFoundException;
import com.wap.chun.match.dtos.*;
import com.wap.chun.match.repository.MatchRepository;
import com.wap.chun.profile.club.dtos.ClubDetailsInfoDto;
import com.wap.chun.profile.club.repository.ClubMemberRepository;
import com.wap.chun.profile.club.repository.ClubRepository;
import com.wap.chun.profile.member.dtos.MemberDetailsInfoDto;
import com.wap.chun.profile.member.repository.MemberRepository;
import com.wap.chun.security.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void saveMatch(MatchSaveDto dto) {
        Club homeClub = clubRepository.findByClubNameAndCityAndDistrictAndDeleteFlagFalse(dto.getHomeClubName(), dto.getHomeClubCity(), dto.getHomeClubDistrict())
                .orElseThrow(ClubNotFoundException::new);
        Club awayClub = clubRepository.findByClubNameAndCityAndDistrictAndDeleteFlagFalse(dto.getAwayClubName(), dto.getAwayClubCity(), dto.getAwayClubDistrict())
                .orElseThrow(ClubNotFoundException::new);

        matchRepository.save(new Match(dto.getDate(), dto.getMatchCity(), dto.getMatchDistrict(), dto.getMatchDetailsAddress(), homeClub, awayClub));
    }

    //클럽의 경기 기록 조회(전체)
    public List<MatchDetailsInfoDto> findClubHistory(String clubName, String clubCity, String clubDistrict) {
        Club club = clubRepository.findByClubNameAndCityAndDistrictAndDeleteFlagFalse(clubName, clubCity, clubDistrict)
                .orElseThrow(ClubNotFoundException::new);

        List<Match> matches = matchRepository.findByHomeClubOrAwayClub(club, club)
                .orElse(new ArrayList<>());
        return matches.stream()
                .map(MatchDetailsInfoDto::new)
                .collect(Collectors.toList());
    }

    //클럽의 경기 기록 조회
    public List<MatchInfoDto> findByClubNameAndClubLocation(String clubName, String clubCity, String clubDistrict,
                                                            LocalDate from, LocalDate to) {
        Club club = clubRepository.findByClubNameAndCityAndDistrictAndDeleteFlagFalse(clubName, clubCity, clubDistrict)
                .orElseThrow(ClubNotFoundException::new);
        List<Match> matches = matchRepository.findByHomeClubOrAwayClubAndDateBetween(club, club, from.atStartOfDay(), to.atTime(23, 59))
                .orElse(new ArrayList<>());

        return matches.stream()
                .map(MatchInfoDto::new)
                .collect(Collectors.toList());
    }

    // 해당 지역에서 한달 내에 치뤄질 경기 검색
    public List<MatchInfoDto> findByLocationInMonth(String city, String district) {
        List<Match> matches = matchRepository.findByCityAndDistrictAndDateBetween(city, district, LocalDateTime.now(), LocalDateTime.now().plusMonths(1))
                .orElse(new ArrayList<>());
        return matches.stream()
                .map(MatchInfoDto::new)
                .collect(Collectors.toList());
    }

    //두 클럽이 붙었었던 기록(간단히 몇대몇)
    public List<MatchInfoDto> findTwoClubHistory(String clubName1, String clubCity1, String clubDistrict1, String clubName2, String clubCity2, String clubDistrict2) {
        List<MatchDetailsInfoDto> detailsDto = this.findTwoClubDetailsHistory(clubName1, clubCity1, clubDistrict1, clubName2, clubCity2, clubDistrict2);
        return detailsDto.stream()
                .map(MatchInfoDto::new)
                .collect(Collectors.toList());
    }

    //두 클럽이 붙었었던 기록(자세히 누가 골을 넣고 스쿼드가 어땠는지)
    public List<MatchDetailsInfoDto> findTwoClubDetailsHistory(String clubName1, String clubCity1, String clubDistrict1, String clubName2, String clubCity2, String clubDistrict2) {
        Club club1 = clubRepository.findByClubNameAndCityAndDistrictAndDeleteFlagFalse(clubName1, clubCity1, clubDistrict1)
                .orElseThrow(ClubNotFoundException::new);
        Club club2 = clubRepository.findByClubNameAndCityAndDistrictAndDeleteFlagFalse(clubName2, clubCity2, clubDistrict2)
                .orElseThrow(ClubNotFoundException::new);

        List<Match> matches = matchRepository.findByHomeClubOrAwayClub(club1, club1).orElse(new ArrayList<>());
        return matches.stream()
                .filter(match -> match.getHomeClub().equals(club2) || match.getAwayClub().equals(club2))
                .map(MatchDetailsInfoDto::new)
                .collect(Collectors.toList());
    }

    public void updateInfo(String token, MatchUpdateSimpleInfoDto dto) {
        Match match = matchRepository.findById(dto.getId())
                .orElseThrow(MatchNotFoundException::new);
        // 각 클럽의 리더 혹은 관리자만 접근가능
        if (!(checkLeader(match.getAwayClub(), token)
                || checkLeader(match.getHomeClub(), token)
                || isAdmin(token))) {
            throw new AccessDeniedAuthenticationException();
        }

        matchRepository.save(match.updateInfo(dto));
    }

    public void updateInfoAfterMatch(String token, MatchUpdateInfoDto dto) {
        Match match = matchRepository.findById(dto.getMatchId())
                .orElseThrow(MatchNotFoundException::new);

        // 각 클럽의 리더 혹은 관리자만 접근가능
        if (!(checkLeader(match.getAwayClub(), token)
                || checkLeader(match.getHomeClub(), token)
                || isAdmin(token))) {
            throw new AccessDeniedAuthenticationException();
        }
        //넣은 골 개수와 스코어는 같아야한다.
        if (dto.getHomeClubGoals().size() != dto.getHomeClubScore()
                || dto.getAwayClubGoals().size() != dto.getAwayClubScore()) {
            throw new InvalidInputValueException();
        }

        //골 또는 어시스트를 한 선수와 스쿼드에 있는 선수를 한번에 조회하기 위해
        Set<String> squadMemberIdList = Stream.concat(dto.getHomeClubSquads().stream(), dto.getAwayClubSquads().stream())
                .map(SquadInfoDto::getMemberId)
                .collect(Collectors.toSet());

        Set<String> goalMemberIdList = Stream.concat(dto.getAwayClubGoals().stream(), dto.getHomeClubGoals().stream())
                .flatMap(o -> Stream.of(o.getAssistMemberId(), o.getGoalMemberId()))
                .collect(Collectors.toSet());

        Set<String> allMemberIDsInMatch = Stream.of(squadMemberIdList, goalMemberIdList)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .filter(s -> !s.equals(""))
                .collect(Collectors.toSet());

        //querydsl을 사용하여 개선(내부적으로는 findbyid랑 다를게 없음)
        Map<String, Member> map = memberRepository.findAllById(allMemberIDsInMatch)
                .stream().collect(Collectors.toMap(Member::getId, Function.identity()));

        List<Goal> goals = new ArrayList<>();
        for (GoalInfoDto o : dto.getHomeClubGoals()) {
            if (o.getTime() < 0 || o.getTime() > 100)
                throw new InvalidInputValueException();

            goals.add(new Goal(o, MatchType.HOME, map.get(o.getGoalMemberId()), match));
        }
        for (GoalInfoDto o : dto.getAwayClubGoals()) {
            if (o.getTime() < 0 || o.getTime() > 100)
                throw new InvalidInputValueException();

            goals.add(new Goal(o, MatchType.AWAY, map.get(o.getGoalMemberId()), match));
        }

        List<Squad> squad = new ArrayList<>();
        for (SquadInfoDto o : dto.getHomeClubSquads()) {
            squad.add(Squad.builder()
                    .match(match)
                    .matchType(MatchType.HOME)
                    .member(map.get(o.getMemberId()))
                    .positionType(o.getPositionType())
                    .build());
        }

        matchRepository.save(match.updateInfoAfterMatch(dto.getHomeClubScore(), dto.getAwayClubScore(), goals, squad));
    }

    private boolean checkLeader(Club club, String token) {
        String username = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(token));
        return club.getClubMembers().stream()
                .filter(o -> o.getClubMemberType().equals(ClubMemberType.LEADER))
                .anyMatch(o -> o.getMember().getId().equals(username));
    }

    private boolean isAdmin(String token) {
        return jwtTokenProvider.hasRole(jwtTokenProvider.resolveToken(token), MemberRole.ADMIN);
    }

    //TODO("아래는 QueryDsl을 적용 후 작성")
    //가장 많은 승점을 취한 클럽(승점을 적용하는 방법은 EPL기준으로)
    public List<ClubDetailsInfoDto> findBestClub(){
        return null;
    }

    //가장 골을 많이 넣은 플레이어
    public List<MemberDetailsInfoDto> findTopScorers(){
        return null;
    }

    //가장 어시스트를 많이한 플레이어
    public List<MemberDetailsInfoDto> findTopAssistants(){
        return null;
    }
}
