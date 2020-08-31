package com.chun.crud.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubDeleteDto {
    private String clubName;
    private String clubCity;
    private String clubDistrict;
}
