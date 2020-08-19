package com.wap.api.profile.club.setup;

import com.wap.api.domain.entitys.Club;
import com.wap.api.profile.club.dtos.response.ClubInfoDto;

public class ClubSetUp {
    public static Club yangPyeongFC = Club.builder()
            .clubName("양평FC")
            .city("경기도")
            .district("양평군")
            .logoUri("/img/logo/yang.png")
            .build();

    public static Club yangpyeongProFC = Club.builder()
            .clubName("양평프로FC")
            .city("경기도")
            .district("양평군")
            .logoUri("/img/logo/yang.png")
            .build();

    public static Club seoulYangpyeongFC = Club.builder()
            .clubName("양평FC")
            .city("서울특별시")
            .district("양평동")
            .logoUri("/img/logo/yang.png")
            .build();

    public static Club gangjuFC = Club.builder()
            .clubName("광주FC")
            .city("경기도")
            .district("광주시")
            .logoUri("/img/logo/gang.png")
            .build();

    public static Club seoulFC = Club.builder()
            .clubName("서울FC")
            .city("서울특별시")
            .district("용산구")
            .logoUri("/img/logo/seoul.png")
            .build();
}
