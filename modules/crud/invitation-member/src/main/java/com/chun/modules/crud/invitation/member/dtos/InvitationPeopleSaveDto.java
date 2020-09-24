package com.chun.modules.crud.invitation.member.dtos;

import com.chun.commons.enums.InvitationType;
import com.chun.modules.crud.invitation.member.entity.InvitationMember;
import com.chun.modules.crud.club.entitys.Club;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
public class InvitationPeopleSaveDto {
    private String clubName;
    private String clubCity;
    private String clubDistrict;
    private InvitationType invitationType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String message;

    public InvitationMember toEntity(Club club){
        return InvitationMember.builder()
                .club(club)
                .invitationType(invitationType)
                .startDate(startDate)
                .endDate(endDate)
                .message(message)
                .build();
    }
}
