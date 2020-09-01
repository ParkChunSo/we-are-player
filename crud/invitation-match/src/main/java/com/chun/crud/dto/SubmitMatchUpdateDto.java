package com.chun.crud.dto;

import com.chun.commons.enums.SubmitState;
import lombok.Getter;

@Getter
public class SubmitMatchUpdateDto {
    private long id;
    private SubmitState state;
}
