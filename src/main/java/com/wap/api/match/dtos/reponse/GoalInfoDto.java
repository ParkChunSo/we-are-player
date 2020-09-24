package com.wap.api.match.dtos.reponse;

import com.wap.api.domain.entitys.Goal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter @AllArgsConstructor
public class GoalInfoDto {
    private String goalMemberId;
    private String goalMemberName;
    private String assistMemberId;
    private String assistMemberName;
    private int time;

    public GoalInfoDto(Goal entity) {
        this.goalMemberId = entity.getGoalMember().getId();
        this.goalMemberName = entity.getGoalMember().getName();
        this.assistMemberId = entity.getAssistMemberId();
        this.assistMemberName = entity.getAssistMemberName();
        this.time = entity.getTime();
    }
}