package com.wap.api.profile.club.setup;

import com.wap.api.profile.club.dtos.response.ClubInfoDto;

public class ClubInfoSetUp {
    public static ClubInfoDto yangpyeongFC = ClubInfoDto.builder()
            .clubName("양평FC")
            .city("경기도")
            .district("양평군")
            .logoUri("/img/logo/yang.png")
            .build();

    public static ClubInfoDto yangpyeongProFC = ClubInfoDto.builder()
            .clubName("양평프로FC")
            .city("경기도")
            .district("양평군")
            .logoUri("/img/logo/yang.png")
            .build();

    public static ClubInfoDto seoulYangpyeongFC = ClubInfoDto.builder()
            .clubName("양평FC")
            .city("서울특별시 양평동")
            .logoUri("/img/logo/yang.png")
            .build();
    
    public static ClubInfoDto gangjuFC = ClubInfoDto.builder()
            .clubName("광주FC")
            .city("경기도 광주시")
            .logoUri("/img/logo/gang.png")
            .build();
    public static ClubInfoDto seoulFC = ClubInfoDto.builder()
            .clubName("서울FC")
            .city("서울특별시")
            .logoUri("/img/logo/seoul.png")
            .build();

}
