package com.chun.crud.invitation.member.dtos;


import com.chun.commons.enums.ClubMemberType;
import com.chun.commons.enums.PositionType;
import com.chun.commons.enums.SubmitState;
import lombok.Getter;

@Getter
public class SubmitPeopleUpdateDto {
    private long id;
    private int uniformNum;
    private ClubMemberType clubMemberType;
    private PositionType positionType;
    private SubmitState state;
}
