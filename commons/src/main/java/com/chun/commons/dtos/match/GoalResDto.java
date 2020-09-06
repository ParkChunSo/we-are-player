package com.chun.commons.dtos.match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter @AllArgsConstructor
public class GoalResDto {
    private String goalMemberId;
    private String goalMemberName;
    private String assistMemberId;
    private String assistMemberName;
    private int time;
}