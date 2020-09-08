package com.chun.crud.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ClubsInfoDto {
    private String clubName1;
    private String clubCity1;
    private String clubDistrict1;

    private String clubName2;
    private String clubCity2;
    private String clubDistrict2;
}
