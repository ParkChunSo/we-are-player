package com.chun.apps.invitation.dtos.request;

import com.chun.crud.invitation.member.dtos.InvitationPeopleUpdateDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InvitationPeopleUpdateReqDto {
    private long id;
    private LocalDateTime endDate;
    private String message;

    public InvitationPeopleUpdateDto toInvitationPeopleUpdateDto(){
        return InvitationPeopleUpdateDto.builder()
                .id(this.id)
                .endDate(this.endDate)
                .message(this.message)
                .build();
    }
}
