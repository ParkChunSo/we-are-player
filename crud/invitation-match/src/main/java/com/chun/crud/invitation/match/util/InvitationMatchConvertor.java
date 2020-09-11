package com.chun.crud.invitation.match.util;

import com.chun.commons.dtos.invitation.InvitationMatchResDto;
import com.chun.commons.dtos.invitation.SubmitMatchResDto;
import com.chun.crud.club.util.ClubConvertor;
import com.chun.crud.invitation.match.entity.InvitationMatch;
import com.chun.crud.invitation.match.entity.SubmitMatch;

import java.util.stream.Collectors;

public class InvitationMatchConvertor {
    public static InvitationMatchResDto toInvitationMatchResDto(InvitationMatch entity){
        return InvitationMatchResDto.builder()
                .id(entity.getId())
                .clubInfo(ClubConvertor.toClubResDto(entity.getClub()))
                .city(entity.getCity())
                .districts(entity.getDistrict())
                .detailsAddress(entity.getDetailsAddress())
                .message(entity.getMessage())
                .date(entity.getDate())
                .dtos(entity.getSubmitMatchList().stream()
                        .map(InvitationMatchConvertor::toSubmitMatchResDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public static SubmitMatchResDto toSubmitMatchResDto(SubmitMatch entity){
        return SubmitMatchResDto.builder()
                .id(entity.getId())
                .club(ClubConvertor.toClubResDto(entity.getClub()))
                .message(entity.getMessage())
                .state(entity.getState())
                .submitTime(entity.getSubmitTime())
                .build();
    }
}
