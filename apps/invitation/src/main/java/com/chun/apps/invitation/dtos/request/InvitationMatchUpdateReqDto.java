package com.chun.apps.invitation.dtos.request;

import com.chun.modules.crud.invitation.match.dtos.InvitationMatchUpdateDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InvitationMatchUpdateReqDto {
    private long id;
    private String city;
    private String district;
    private String detailsAddress;
    private LocalDateTime date;
    private String message;

    public InvitationMatchUpdateDto toInvitationMatchUpdateDto(){
        return InvitationMatchUpdateDto.builder()
                .id(this.id)
                .city(this.city)
                .district(this.district)
                .detailsAddress(this.detailsAddress)
                .date(this.date)
                .message(this.message)
                .build();
    }
}
