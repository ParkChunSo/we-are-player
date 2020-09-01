package com.chun.crud.dtos;


import com.chun.commons.enums.ClubMemberType;
import com.chun.commons.enums.PositionType;
import com.chun.commons.enums.SubmitState;
import lombok.Getter;

@Getter
public class SubmitMemberUpdateDto {
    private long id;
    private int uniformNum;
    private ClubMemberType clubMemberType;
    private PositionType positionType;
    private SubmitState state;
}
