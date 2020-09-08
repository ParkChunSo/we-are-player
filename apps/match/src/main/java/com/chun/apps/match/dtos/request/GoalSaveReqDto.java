package com.chun.apps.match.dtos.request;

import com.chun.crud.dtos.GoalSaveDto;
import lombok.Getter;

@Getter
public class GoalSaveReqDto {
    private String clubName;
    private String goalMemberId;
    private String assistMemberId;
    private int time;

    public GoalSaveDto toGoalSaveDto(){
        return GoalSaveDto.builder()
                .clubName(this.clubName)
                .goalMemberId(this.goalMemberId)
                .assistMemberId(this.assistMemberId)
                .time(this.time)
                .build();
    }
}
