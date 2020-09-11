package com.chun.apps.invitation.dtos.request;

import com.chun.commons.enums.InvitationType;
import com.chun.crud.invitation.member.dtos.InvitationPeopleSaveDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class InvitationPeopleSaveReqDto {
    private InvitationType type;

    private String clubName;
    private String clubCity;
    private String clubDistrict;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String message;

    public InvitationPeopleSaveDto toInvitationPeopleSaveDto(){
        return InvitationPeopleSaveDto.builder()
                .clubCity(this.clubCity)
                .clubDistrict(this.clubDistrict)
                .clubName(this.clubName)
                .invitationType(this.type)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .message(this.message)
                .build();
    }
}
