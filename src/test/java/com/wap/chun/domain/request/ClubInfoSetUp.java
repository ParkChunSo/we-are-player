package com.wap.chun.domain.request;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.profile.club.dtos.ClubInfoDto;

public class ClubInfoSetUp {
    public static ClubInfoDto yangpyeongFC = ClubInfoDto.builder()
            .clubName("양평FC")
            .location("경기도 양평")
            .logoUri("/img/logo/yang.png")
            .build();

    public static ClubInfoDto yangpyeongProFC = ClubInfoDto.builder()
            .clubName("양평프로FC")
            .location("경기도 양평")
            .logoUri("/img/logo/yang.png")
            .build();

    public static ClubInfoDto seoulYangpyeongFC = ClubInfoDto.builder()
            .clubName("양평FC")
            .location("서울특별시 양평동")
            .logoUri("/img/logo/yang.png")
            .build();
    
    public static ClubInfoDto gangjuFC = ClubInfoDto.builder()
            .clubName("광주FC")
            .location("경기도 광주")
            .logoUri("/img/logo/gang.png")
            .build();
    public static ClubInfoDto seoulFC = ClubInfoDto.builder()
            .clubName("서울FC")
            .location("서울특별시")
            .logoUri("/img/logo/seoul.png")
            .build();
}
