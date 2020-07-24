package com.wap.chun.domain.builder;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.Member;

public class ClubBuilder {
    public static Club yangpyeongFC = Club.builder()
            .clubName("양평FC")
            .location("경기도 양평군")
            .logoUri("/path/logo/img.png")
            .build();

    public static Club build(String clubName) {
        return Club.builder()
                .clubName(clubName)
                .location("경기도 양평군")
                .logoUri("/path/logo/img.png")
                .build();
    }

    public static Club build(String clubName, String clubLocation) {
        return Club.builder()
                .clubName(clubName)
                .location(clubLocation)
                .logoUri("/path/logo/img.png")
                .build();
    }
}
