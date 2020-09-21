package com.chun.modules.crud.club.dtos;

import lombok.*;

@Getter
@Builder
public class ClubSaveDto {
    private String clubName;
    private String clubCity;
    private String clubDistrict;
    private String logoUri;
    private ClubMemberSaveDto leader;
}
