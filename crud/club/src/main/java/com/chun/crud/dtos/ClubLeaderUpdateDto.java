package com.chun.crud.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ClubLeaderUpdateDto {
    private String clubName;
    private String city;
    private String district;

    private String preLeaderId;
    private String newLeaderId;
}

