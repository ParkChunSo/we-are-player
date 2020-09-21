package com.chun.modules.crud.invitation.match.dtos;

import com.chun.modules.crud.club.entitys.Club;
import com.chun.modules.crud.invitation.match.entity.InvitationMatch;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
public class InvitationMatchSaveDto {

    private String city;
    private String district;
    private String detailsAddress;

    private String clubName;
    private String clubCity;
    private String clubDistrict;

    private LocalDateTime date;
    private String message;

    public InvitationMatch toEntity(Club club){
        return InvitationMatch.builder()
                .city(city)
                .district(district)
                .detailsAddress(detailsAddress)
                .club(club)
                .date(date)
                .message(message)
                .build();
    }
}
