package com.wap.api.match.dtos.reponse;

import com.wap.api.domain.entitys.Goal;
import com.wap.api.domain.entitys.Match;
import com.wap.api.domain.entitys.Squad;
import com.wap.api.domain.enums.MatchType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class MatchDetailsInfoDto extends MatchInfoDto{
    Map<MatchType, List<GoalInfoDto>> goalMap;
    Map<MatchType, List<SquadInfoDto>> squadMap;

    public MatchDetailsInfoDto(Match entity) {
        super(entity);
        goalMap = entity.getGoals().stream().collect(
                Collectors.groupingBy(Goal::getType, Collectors.mapping(GoalInfoDto::new, Collectors.toList())));
        squadMap = entity.getSquads().stream().collect(
                Collectors.groupingBy(Squad::getMatchType, Collectors.mapping(SquadInfoDto::new, Collectors.toList())));
    }
}
