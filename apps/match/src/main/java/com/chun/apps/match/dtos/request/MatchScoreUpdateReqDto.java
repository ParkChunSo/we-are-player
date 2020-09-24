package com.chun.apps.match.dtos.request;

import com.chun.modules.crud.dtos.MatchScoreUpdateDto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MatchScoreUpdateReqDto {
    private long id;

    private int homeClubScore;
    private int awayClubScore;

    private List<GoalSaveReqDto> goalList;
    private List<SquadSaveReqDto> squadList;

    public MatchScoreUpdateDto toMatchScoreUpdateDto(){
        return MatchScoreUpdateDto.builder()
                .matchId(this.id)
                .homeClubScore(this.homeClubScore)
                .awayClubScore(this.awayClubScore)
                .goalSaveDtos(goalList.stream()
                        .map(GoalSaveReqDto::toGoalSaveDto)
                        .collect(Collectors.toList()))
                .squadSaveDtos(squadList.stream()
                        .map(SquadSaveReqDto::toSquadSaveDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
