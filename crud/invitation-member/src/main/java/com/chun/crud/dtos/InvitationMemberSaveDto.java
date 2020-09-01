package com.chun.crud.dtos;

import com.chun.commons.enums.InvitationType;
import com.chun.crud.entity.InvitationMember;
import com.chun.crud.entitys.Club;
import com.sun.istack.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InvitationMemberSaveDto {
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
