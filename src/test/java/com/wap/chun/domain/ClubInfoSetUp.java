package com.wap.chun.domain;

import com.wap.chun.profile.club.dtos.ClubInfoDto;

public class ClubInfoSetUp {
    public static ClubInfoDto dto = ClubInfoDto.builder()
            .clubName("양평FC")
            .leaderId("park@gmail.com")
            .leaderName("박춘소")
            .location("양평")
            .logoUri("/img/logo/park.png")
            .build();
}
