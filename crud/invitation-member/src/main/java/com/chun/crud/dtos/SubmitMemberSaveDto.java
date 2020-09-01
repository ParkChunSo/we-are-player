package com.chun.crud.dtos;

import com.chun.commons.enums.ClubMemberType;
import lombok.Getter;

@Getter
public class SubmitMemberSaveDto {
    private long invitationId;
    private String message;
    private String memberId;
    private ClubMemberType clubMemberType;
}
