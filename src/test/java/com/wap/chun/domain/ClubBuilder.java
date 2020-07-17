package com.wap.chun.domain;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.Member;

public class ClubBuilder {
    public static Club build(Member leader){
        return Club.builder()
                .clubName("양평FC")
                .location("경기도 양평군")
                .leader(leader)
                .build();
    }
}
