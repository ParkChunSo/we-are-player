package com.wap.chun.match.dtos;

import com.wap.chun.domain.entitys.Goal;
import com.wap.chun.domain.entitys.Match;
import com.wap.chun.domain.entitys.Squad;
import com.wap.chun.domain.enums.MatchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class MatchDetailsInfoDto extends MatchInfoDto{
    List<GoalInfoDto> homeClubGoals = new ArrayList<>();
    List<GoalInfoDto> awayClubGoals = new ArrayList<>();

    List<SquadInfoDto> homeClubSquad = new ArrayList<>();
    List<SquadInfoDto> awayClubSquad = new ArrayList<>();

    public MatchDetailsInfoDto(Match entity) {
        super(entity);
        for(Goal goal : entity.getGoals()){
            if(goal.getType().equals(MatchType.HOME))
                homeClubGoals.add(new GoalInfoDto(goal));
            else
                awayClubGoals.add(new GoalInfoDto(goal));
        }

        for(Squad squad : entity.getSquads()){
            if(squad.getMatchType().equals(MatchType.HOME))
                homeClubSquad.add(new SquadInfoDto(squad));
            else
                awayClubSquad.add(new SquadInfoDto(squad));
        }
    }
}
