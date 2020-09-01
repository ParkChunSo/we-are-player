package com.chun.crud.dtos;

import lombok.Getter;

@Getter
public class GoalSaveDto {
    private String clubName;
    private String goalMemberId;
    private String assistMemberId;
    private int time;
}
