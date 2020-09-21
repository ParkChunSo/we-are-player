package com.chun.commons.dtos.match;


import com.chun.commons.dtos.club.ClubResDto;
import com.chun.commons.enums.MatchState;
import com.chun.commons.enums.MatchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@SuperBuilder
public class MatchDetailsResDto extends MatchResDto {
    Map<MatchType, List<GoalResDto>> goalMap;
    Map<MatchType, List<SquadResDto>> squadMap;

    //    public MatchDetailsInfoDto(Match entity) {
//        super(entity);
//        goalMap = entity.getGoals().stream().collect(
//                Collectors.groupingBy(Goal::getType, Collectors.mapping(GoalInfoDto::new, Collectors.toList())));
//        squadMap = entity.getSquads().stream().collect(
//                Collectors.groupingBy(Squad::getMatchType, Collectors.mapping(SquadInfoDto::new, Collectors.toList())));
//    }
}
