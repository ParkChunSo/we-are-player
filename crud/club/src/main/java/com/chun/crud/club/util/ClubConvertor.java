package com.chun.crud.club.util;

import com.chun.commons.dtos.club.ClubMemberResDto;
import com.chun.commons.dtos.club.ClubResDto;
import com.chun.crud.entitys.Club;
import com.chun.crud.entitys.ClubMember;

import java.util.stream.Collectors;

public class ClubConvertor {
    public static ClubResDto toClubResDto(Club entity){
        return ClubResDto.builder()
                .clubId(entity.getClubId())
                .city(entity.getCity())
                .district(entity.getDistrict())
                .clubName(entity.getClubName())
                .logoUri(entity.getLogoUri())
                .members(entity.getClubMembers().stream().map(ClubConvertor::toClubMemberResDto).collect(Collectors.toList()))
                .build();
    }

    public static ClubMemberResDto toClubMemberResDto(ClubMember entity){
        return ClubMemberResDto.builder()
                .memberId(entity.getMember().getId())
                .memberName(entity.getMember().getName())
                .city(entity.getMember().getCity())
                .district(entity.getMember().getDistrict())
                .pictureUri(entity.getMember().getPictureUri())
                .position(entity.getPositionType())
                .registTime(entity.getRegistDate().toString())
                .type(entity.getClubMemberType())
                .uniformNum(entity.getUniformNum())
                .build();
    }
}
