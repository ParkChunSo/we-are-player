package com.chun.crud.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class GoalSaveDto {
    private String clubName;
    private String goalMemberId;
    private String assistMemberId;
    private int time;
}
