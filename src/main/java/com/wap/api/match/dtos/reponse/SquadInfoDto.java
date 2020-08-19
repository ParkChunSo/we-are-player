package com.wap.api.match.dtos.reponse;

import com.wap.api.domain.entitys.Squad;
import com.wap.api.domain.enums.PositionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter @Builder
public class SquadInfoDto {
    private String memberId;
    private String memberName;
    private PositionType positionType;

    public SquadInfoDto(Squad entity){
        this.memberId = entity.getMember().getId();
        this.memberName = entity.getMember().getName();
        this.positionType = entity.getPositionType();
    }
}
