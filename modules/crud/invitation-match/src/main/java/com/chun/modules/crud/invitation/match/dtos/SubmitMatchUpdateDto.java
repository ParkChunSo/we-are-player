package com.chun.modules.crud.invitation.match.dtos;

import com.chun.commons.enums.SubmitState;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class SubmitMatchUpdateDto {
    private long id;
    private SubmitState state;
}
