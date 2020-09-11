package com.chun.apps.invitation.dtos.request;

import com.chun.commons.enums.SubmitState;
import com.chun.crud.invitation.match.dtos.SubmitMatchUpdateDto;
import lombok.Getter;

@Getter
public class SubmitMatchUpdateReqDto {
    private long invitationId;
    private long submitId;
    private SubmitState state;

    public SubmitMatchUpdateDto toSubmitMatchUpdateDto(){
        return SubmitMatchUpdateDto.builder()
                .id(this.submitId)
                .state(this.state)
                .build();
    }
}
