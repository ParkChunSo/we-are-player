package com.chun.crud.dtos;

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
