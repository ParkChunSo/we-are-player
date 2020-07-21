package com.wap.chun.domain.builder;

import com.wap.chun.profile.club.dtos.ClubInfoUpdateDto;
import lombok.Getter;

@Getter
public class ClubInfoUpdateDtoSetUp {
    public static ClubInfoUpdateDto dto = ClubInfoUpdateDto.builder()
            .clubName("양평FC")
            .location("경기도 양평")
            .likeCnt(12)
            .rudeCnt(4)
            .logoUri("path")
            .build();
}
