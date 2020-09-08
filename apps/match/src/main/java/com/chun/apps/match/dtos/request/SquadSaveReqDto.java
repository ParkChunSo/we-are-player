package com.chun.apps.match.dtos.request;

import com.chun.commons.enums.PositionType;
import com.chun.crud.dtos.SquadSaveDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SquadSaveReqDto {
    private String clubName;
    private String memberId;
    private PositionType positionType;

    public SquadSaveDto toSquadSaveDto(){
        return SquadSaveDto.builder()
                .clubName(this.clubName)
                .memberId(this.memberId)
                .positionType(this.positionType)
                .build();
    }
}
