package com.chun.commons.dtos.match;


import com.chun.commons.dtos.club.ClubResDto;
import com.chun.commons.enums.MatchState;
import com.chun.commons.enums.MatchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class MatchDetailsResDto extends MatchResDto {
    Map<MatchType, List<GoalResDto>> goalMap;
    Map<MatchType, List<SquadResDto>> squadMap;

    @Builder
    public MatchDetailsResDto(long id, LocalDateTime date, String city, String district, String detailsAddress, ClubResDto homeClub, ClubResDto awayClub, int homeClubScore, int awayClubScore, MatchState state, Map<MatchType, List<GoalResDto>> goalMap, Map<MatchType, List<SquadResDto>> squadMap) {
        super(id, date, city, district, detailsAddress, homeClub, awayClub, homeClubScore, awayClubScore, state);
        this.goalMap = goalMap;
        this.squadMap = squadMap;
    }

    //    public MatchDetailsInfoDto(Match entity) {
//        super(entity);
//        goalMap = entity.getGoals().stream().collect(
//                Collectors.groupingBy(Goal::getType, Collectors.mapping(GoalInfoDto::new, Collectors.toList())));
//        squadMap = entity.getSquads().stream().collect(
//                Collectors.groupingBy(Squad::getMatchType, Collectors.mapping(SquadInfoDto::new, Collectors.toList())));
//    }
}
