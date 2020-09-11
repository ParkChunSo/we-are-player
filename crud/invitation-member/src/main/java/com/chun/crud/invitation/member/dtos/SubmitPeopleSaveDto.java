package com.chun.crud.invitation.member.dtos;

import com.chun.commons.enums.ClubMemberType;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class SubmitPeopleSaveDto {
    private long invitationId;
    private String message;
    private String memberId;
    private ClubMemberType clubMemberType;
}
