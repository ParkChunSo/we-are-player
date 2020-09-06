package com.chun.commons.dtos.match;


import com.chun.commons.enums.MatchType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class MatchDetailsInfoDto extends MatchResDto {
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
