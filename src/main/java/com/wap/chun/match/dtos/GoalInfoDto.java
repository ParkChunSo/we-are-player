package com.wap.chun.match.dtos;

import com.wap.chun.domain.entitys.Goal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Builder
@Getter @AllArgsConstructor
public class GoalInfoDto {
    private String goalMemberId;
    private String goalMemberName;
    private String assistMemberId;
    private String assistMemberName;
    private Integer time;

    public GoalInfoDto(Goal entity) {
        this.goalMemberId = entity.getGoalMember().getId();
        this.goalMemberName = entity.getGoalMember().getName();
        this.assistMemberId = entity.getAssistMemberId();
        this.assistMemberName = entity.getAssistMemberName();
        this.time = entity.getTime();
    }
}