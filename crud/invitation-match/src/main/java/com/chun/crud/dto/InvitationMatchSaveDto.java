package com.chun.crud.dto;

import com.chun.commons.enums.InvitationType;
import com.chun.crud.entity.InvitationMatch;
import com.chun.crud.entitys.Club;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InvitationMatchSaveDto {
    private InvitationType category;

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
