package com.wap.api.profile.club.setup;

import com.wap.api.domain.entitys.Member;
import com.wap.api.domain.enums.DisclosureScopeState;
import com.wap.api.domain.enums.MemberRole;
import com.wap.api.domain.enums.MemberType;
import com.wap.api.domain.enums.PositionType;
import com.wap.api.profile.member.dtos.MemberSignUpDto;

import java.util.Set;

public class MemberSetUp {
    public static Member park = Member.builder()
            .id("park@gmail.com")
            .password("1234")
            .name("박춘소")
            .city("경기도")
            .district("양평군")
            .pictureUri("/image/profile/park")
            .roleSet(Set.of(MemberRole.CLIENT))
            .position(PositionType.FW)
            .build();

    public static Member yun = Member.builder()
            .id("yun@gmail.com")
            .password("12345")
            .name("김윤상")
            .city("경기도")
            .district("광주시")
            .pictureUri("/image/profile/yun")
            .roleSet(Set.of(MemberRole.CLIENT))
            .position(PositionType.DF)
            .build();

    public static Member kim = Member.builder()
            .id("kim@gmail.com")
            .password("1234")
            .name("김건영")
            .city("경기도")
            .district("용인시")
            .pictureUri("/image/profile/kim")
            .roleSet(Set.of(MemberRole.CLIENT))
            .position(PositionType.MF)
            .build();

    public static Member jeoung = Member.builder()
            .id("jeoung@gmail.com")
            .password("1234")
            .name("정승현")
            .city("경기도")
            .district("부천시")
            .pictureUri("/image/profile/jeoung")
            .roleSet(Set.of(MemberRole.CLIENT))
            .position(PositionType.GK)
            .build();
}
