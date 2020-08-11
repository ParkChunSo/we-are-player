package com.wap.chun.match.dtos;

import com.wap.chun.domain.enums.MatchState;
import lombok.Getter;

import java.util.List;

@Getter
public class MatchUpdateInfoDto {
    private long matchId;

    private int homeClubScore;
    private int awayClubScore;

    private List<GoalInfoDto> homeClubGoals;
    private List<GoalInfoDto> awayClubGoals;

    private List<SquadInfoDto> homeClubSquads;
    private List<SquadInfoDto> awayClubSquads;
}
