package com.chun.modules.crud.invitation.member.dtos;


import com.chun.commons.enums.ClubMemberType;
import com.chun.commons.enums.PositionType;
import com.chun.commons.enums.SubmitState;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class SubmitPeopleUpdateDto {
    private long id;
    private int uniformNum;
    private ClubMemberType clubMemberType;
    private PositionType positionType;
    private SubmitState state;
}
