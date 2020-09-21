package com.chun.modules.crud.club.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubInfoDto {
    private String clubName;
    private String clubCity;
    private String clubDistrict;
}
