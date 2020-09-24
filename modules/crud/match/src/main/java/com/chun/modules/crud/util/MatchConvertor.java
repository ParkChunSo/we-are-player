package com.chun.modules.crud.util;

import com.chun.commons.dtos.match.GoalResDto;
import com.chun.commons.dtos.match.MatchDetailsResDto;
import com.chun.commons.dtos.match.MatchResDto;
import com.chun.commons.dtos.match.SquadResDto;
import com.chun.commons.enums.MatchType;
import com.chun.modules.crud.club.util.ClubConvertor;
import com.chun.modules.crud.entity.Goal;
import com.chun.modules.crud.entity.Match;
import com.chun.modules.crud.entity.Squad;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MatchConvertor {
    public static MatchResDto toMatchResDto(Match entity){
        return MatchResDto.builder()
                .id(entity.getId())
                .homeClub(ClubConvertor.toClubResDto(entity.getHomeClub()))
                .awayClub(ClubConvertor.toClubResDto(entity.getAwayClub()))
                .homeClubScore(entity.getHomeClubScore())
                .awayClubScore(entity.getAwayClubScore())
                .city(entity.getCity())
                .district(entity.getDistrict())
                .detailsAddress(entity.getDetailsAddress())
                .date(entity.getDate())
                .state(entity.getState())
                .build();
    }

    public static MatchResDto toMatchResDto(MatchDetailsResDto matchDetailsResDto){
        return MatchResDto.builder()
                .id(matchDetailsResDto.getId())
                .homeClub(matchDetailsResDto.getHomeClub())
                .awayClub(matchDetailsResDto.getAwayClub())
                .homeClubScore(matchDetailsResDto.getHomeClubScore())
                .awayClubScore(matchDetailsResDto.getAwayClubScore())
                .city(matchDetailsResDto.getCity())
                .district(matchDetailsResDto.getDistrict())
                .detailsAddress(matchDetailsResDto.getDetailsAddress())
                .date(matchDetailsResDto.getDate())
                .state(matchDetailsResDto.getState())
                .build();
    }

    public static MatchDetailsResDto toMatchDetailsResDto(Match entity){
        Map<MatchType, List<GoalResDto>> goalMap = entity.getGoals().stream().collect(
                Collectors.groupingBy(Goal::getType, Collectors.mapping(MatchConvertor::toGoalResDto, Collectors.toList())));

        Map<MatchType, List<SquadResDto>> squadMap = entity.getSquads().stream().collect(
                Collectors.groupingBy(Squad::getMatchType, Collectors.mapping(MatchConvertor::toSquadResDto, Collectors.toList())));

        return MatchDetailsResDto.builder()
                .id(entity.getId())
                .homeClub(ClubConvertor.toClubResDto(entity.getHomeClub()))
                .awayClub(ClubConvertor.toClubResDto(entity.getAwayClub()))
                .homeClubScore(entity.getHomeClubScore())
                .awayClubScore(entity.getAwayClubScore())
                .city(entity.getCity())
                .district(entity.getDistrict())
                .detailsAddress(entity.getDetailsAddress())
                .date(entity.getDate())
                .state(entity.getState())
                .goalMap(goalMap)
                .squadMap(squadMap)
                .build();
    }

    public static GoalResDto toGoalResDto(Goal entity){
        return GoalResDto.builder()
                .goalMemberId(entity.getGoalMember().getId())
                .goalMemberName(entity.getGoalMember().getName())
                .assistMemberId(entity.getAssistMemberId())
                .assistMemberName(entity.getAssistMemberName())
                .time(entity.getTime())
                .build();
    }

    public static SquadResDto toSquadResDto(Squad entity){
        return SquadResDto.builder()
                .memberId(entity.getMember().getId())
                .memberName(entity.getMember().getName())
                .positionType(entity.getPositionType())
                .build();
    }
}
