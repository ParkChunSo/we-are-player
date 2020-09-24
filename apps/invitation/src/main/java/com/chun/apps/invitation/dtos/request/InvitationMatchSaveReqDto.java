package com.chun.apps.invitation.dtos.request;

import com.chun.modules.crud.invitation.match.dtos.InvitationMatchSaveDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class InvitationMatchSaveReqDto {
    private String city;
    private String district;
    private String detailsAddress;
    private LocalDateTime date;

    private String clubName;
    private String clubCity;
    private String clubDistrict;

    private String message;

    public InvitationMatchSaveDto toInvitationMatchSaveDto(){
        return InvitationMatchSaveDto.builder()
                .city(this.city)
                .district(this.district)
                .detailsAddress(this.detailsAddress)
                .date(this.date)
                .clubCity(this.clubCity)
                .clubDistrict(this.clubDistrict)
                .clubName(this.clubName)
                .message(this.message)
                .build();
    }
}
