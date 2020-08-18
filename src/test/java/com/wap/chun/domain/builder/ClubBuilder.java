package com.wap.chun.domain.builder;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.Member;

public class ClubBuilder {
    public static Club yangpyeongFC = Club.builder()
            .clubName("양평FC")
            .city("경기도")
            .district("양평군")
            .logoUri("/path/logo/img.png")
            .build();

    public static Club build(String clubName) {
        return Club.builder()
                .clubName(clubName)
                .city("경기도")
                .district("양평군")
                .logoUri("/path/logo/img.png")
                .build();
    }

    public static Club build(String clubName, String clubCity, String clubDistrict) {
        return Club.builder()
                .clubName(clubName)
                .city(clubCity)
                .district(clubDistrict)
                .logoUri("/path/logo/img.png")
                .build();
    }
}
