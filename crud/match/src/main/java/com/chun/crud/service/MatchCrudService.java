package com.chun.crud.service;

import com.chun.commons.enums.MatchType;
import com.chun.commons.errors.exception.ClubNotFoundException;
import com.chun.commons.errors.exception.MatchNotFoundException;
import com.chun.crud.dtos.*;
import com.chun.crud.entity.Goal;
import com.chun.crud.entity.Match;
import com.chun.crud.entity.Squad;
import com.chun.crud.entitys.Club;
import com.chun.crud.entitys.Member;
import com.chun.crud.repository.ClubCrudRepository;
import com.chun.crud.repository.MatchCrudRepository;
import com.chun.crud.repository.MemberCrudRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class MatchCrudService {
    private final MatchCrudRepository matchCrudRepository;
    private final MemberCrudRepository memberCrudRepository;
    private final ClubCrudRepository clubCrudRepository;

    public Match save(MatchSaveDto dto) {
        Club homeClub = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getHomeClubName(), dto.getHomeClubCity(), dto.getHomeClubDistrict())
                .orElseThrow(ClubNotFoundException::new);
        Club awayClub = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getAwayClubName(), dto.getAwayClubCity(), dto.getAwayClubDistrict())
                .orElseThrow(ClubNotFoundException::new);

        return matchCrudRepository.save(Match.builder()
                .awayClub(awayClub)
                .homeClub(homeClub)
                .city(dto.getMatchCity())
                .district(dto.getMatchDistrict())
                .detailsAddress(dto.getMatchDetailsAddress())
                .date(dto.getDate())
                .build());
    }

    //클럽의 경기 기록 조회(전체)
    public List<Match> findByClub(ClubInfoDto dto) {
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getClubCity(), dto.getClubDistrict())
                .orElseThrow(ClubNotFoundException::new);

        return matchCrudRepository.findByHomeClubOrAwayClub(club, club)
                .orElse(new ArrayList<>());
    }

    //클럽의 경기 기록 조회
    public List<Match> findByClubAndTime(ClubInfoDto clubDto, MatchTimeDto timeDto) {
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(clubDto.getClubName(), clubDto.getClubCity(), clubDto.getClubDistrict())
                .orElseThrow(ClubNotFoundException::new);
        return matchCrudRepository.findByHomeClubOrAwayClubAndDateBetween(club, club, timeDto.getFrom().atStartOfDay(), timeDto.getTo().atTime(23, 59))
                .orElse(new ArrayList<>());
    }

    // 해당 지역에서 한달 내에 치뤄질 경기 검색
    public List<Match> findByLocationInMonth(MatchLocationDto dto) {
        return matchCrudRepository.findByCityAndDistrictAndDateBetween(dto.getCity(), dto.getDistrict(), LocalDateTime.now(), LocalDateTime.now().plusMonths(1))
                .orElse(new ArrayList<>());
    }


    public Match update(MatchUpdateDto dto) {
        Match match = matchCrudRepository.findById(dto.getId())
                .orElseThrow(MatchNotFoundException::new);

        return matchCrudRepository.save(match.updateInfo(dto.getDate(), dto.getCity(), dto.getDistrict(), dto.getDetailsAddress()));
    }

    public Match updateScore(MatchScoreUpdateDto dto) {
        Match match = matchCrudRepository.findById(dto.getMatchId())
                .orElseThrow(MatchNotFoundException::new);


        List<String> memberIDList = Stream.concat(dto.getGoalSaveDtos().stream().map(GoalSaveDto::getGoalMemberId),
                Stream.concat(dto.getGoalSaveDtos().stream().map(GoalSaveDto::getAssistMemberId),
                        dto.getSquadSaveDtos().stream().map(SquadSaveDto::getMemberId)))
                .collect(Collectors.toList());
        Map<String, Member> memberMap = memberCrudRepository.findAllById(memberIDList).stream()
                .collect(Collectors.toMap(Member::getId, Function.identity()));

        List<Goal> goalList = new ArrayList<>();
        for (GoalSaveDto goal : dto.getGoalSaveDtos()) {
            if (match.getHomeClub().getClubName().equals(goal.getClubName())) {
                goalList.add(Goal.builder()
                        .assistMemberId(memberMap.get(goal.getAssistMemberId()).getId())
                        .assistMemberName(memberMap.get(goal.getAssistMemberId()).getName())
                        .goalMember(memberMap.get(goal.getGoalMemberId()))
                        .match(match)
                        .time(goal.getTime())
                        .type(MatchType.HOME)
                        .build());
            } else {
                goalList.add(Goal.builder()
                        .assistMemberId(memberMap.get(goal.getAssistMemberId()).getId())
                        .assistMemberName(memberMap.get(goal.getAssistMemberId()).getName())
                        .goalMember(memberMap.get(goal.getGoalMemberId()))
                        .match(match)
                        .time(goal.getTime())
                        .type(MatchType.AWAY)
                        .build());
            }
        }

        List<Squad> squadList = new ArrayList<>();
        for (SquadSaveDto squadDto : dto.getSquadSaveDtos()) {
            if (match.getHomeClub().getClubName().equals(squadDto.getClubName())) {
                squadList.add(
                        Squad.builder()
                                .match(match)
                                .matchType(MatchType.HOME)
                                .member(memberMap.get(squadDto.getMemberId()))
                                .positionType(squadDto.getPositionType())
                                .build());
            } else {
                squadList.add(
                        Squad.builder()
                                .match(match)
                                .matchType(MatchType.HOME)
                                .member(memberMap.get(squadDto.getMemberId()))
                                .positionType(squadDto.getPositionType())
                                .build());
            }
        }

        return matchCrudRepository.save(match.updateInfoAfterMatch(dto.getHomeClubScore(), dto.getAwayClubScore(), goalList, squadList));
    }

    public void delete(Long matchId){
        matchCrudRepository.deleteById(matchId);
    }
}
