package com.chun.crud.dtos;

import lombok.Getter;

@Getter
public class ClubLeaderUpdateDto {
    private String clubName;
    private String city;
    private String district;

    private String preLeaderId;
    private String newLeaderId;
}

