package com.chun.crud.dtos;

import com.chun.commons.enums.PositionType;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class SquadSaveDto {
    private String clubName;
    private String memberId;
    private PositionType positionType;
}
