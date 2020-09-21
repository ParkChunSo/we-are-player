package com.chun.apps.invitation.dtos.request;

import com.chun.commons.enums.ClubMemberType;
import com.chun.commons.enums.PositionType;
import com.chun.commons.enums.SubmitState;
import com.chun.modules.crud.invitation.member.dtos.SubmitPeopleUpdateDto;
import lombok.Getter;

@Getter
public class SubmitPeopleUpdateReqDto {
    private long invitationId;
    private long submitId;
    private int uniformNum;
    private ClubMemberType clubMemberType;
    private PositionType positionType;
    private SubmitState state;

    public SubmitPeopleUpdateDto toSubmitPeopleUpdateDto(){
        return SubmitPeopleUpdateDto.builder()
                .id(this.submitId)
                .uniformNum(this.uniformNum)
                .clubMemberType(this.clubMemberType)
                .positionType(this.positionType)
                .state(this.state)
                .build();
    }
}
