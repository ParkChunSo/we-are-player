package com.wap.chun.domain.request;

import com.wap.chun.profile.club.dtos.ClubInfoUpdateDto;
import lombok.Getter;

@Getter
public class ClubInfoUpdateDtoSetUp {
    public static ClubInfoUpdateDto dto = ClubInfoUpdateDto.builder()
            .clubName("양평FC")
            .city("경기도")
            .district("양평")
            .likeCnt(12)
            .rudeCnt(4)
            .logoUri("path")
            .build();
}
