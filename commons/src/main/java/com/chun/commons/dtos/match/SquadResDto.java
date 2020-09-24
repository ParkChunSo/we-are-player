package com.chun.commons.dtos.match;


import com.chun.commons.enums.PositionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter @Builder
public class SquadResDto {
    private String memberId;
    private String memberName;
    private PositionType positionType;
}
