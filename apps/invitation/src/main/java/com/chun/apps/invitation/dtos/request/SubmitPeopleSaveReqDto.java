package com.chun.apps.invitation.dtos.request;

import com.chun.commons.enums.ClubMemberType;
import com.chun.crud.invitation.member.dtos.SubmitPeopleSaveDto;
import lombok.Getter;

@Getter
public class SubmitPeopleSaveReqDto {
    private long invitationId;
    private String message;
    private String memberId;
    private ClubMemberType clubMemberType;

    public SubmitPeopleSaveDto toSubmitPeopleSaveDto(){
        return SubmitPeopleSaveDto.builder()
                .invitationId(this.invitationId)
                .memberId(this.memberId)
                .message(this.message)
                .clubMemberType(this.clubMemberType)
                .build();
    }
}
