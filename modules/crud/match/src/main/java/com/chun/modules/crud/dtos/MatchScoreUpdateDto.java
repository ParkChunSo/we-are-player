package com.chun.modules.crud.dtos;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MatchScoreUpdateDto {
    private long matchId;

    private int homeClubScore;
    private int awayClubScore;

    private List<GoalSaveDto> goalSaveDtos;
    private List<SquadSaveDto> squadSaveDtos;
}
