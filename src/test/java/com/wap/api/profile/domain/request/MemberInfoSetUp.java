package com.wap.api.profile.domain.request;

import com.wap.api.profile.domain.entitys.Member;
import com.wap.api.profile.domain.enums.DisclosureScopeState;
import com.wap.api.profile.domain.enums.MemberRole;
import com.wap.api.profile.domain.enums.MemberType;
import com.wap.api.profile.domain.enums.PositionType;
import com.wap.api.profile.member.dtos.MemberSignUpDto;
import lombok.Getter;

import java.util.Set;

@Getter
public class MemberInfoSetUp {

    public static MemberSignUpDto park = MemberSignUpDto.builder()
            .id("park@gmail.com")
            .password("1234")
            .name("박춘소")
            .city("경기도")
            .district("양평군")
            .pictureUri("/image/profile/park")
            .type(MemberType.CLIENT)
            .position(PositionType.FW)
            .disclosureScopeState(DisclosureScopeState.PUBLIC)
            .build();

    public static MemberSignUpDto yun = MemberSignUpDto.builder()
            .id("yun@gmail.com")
            .password("12345")
            .name("김윤상")
            .city("경기도")
            .district("광주시")
            .pictureUri("/image/profile/yun")
            .type(MemberType.CLIENT)
            .position(PositionType.DF)
            .disclosureScopeState(DisclosureScopeState.PUBLIC)
            .build();

    public static MemberSignUpDto kim = MemberSignUpDto.builder()
            .id("kim@gmail.com")
            .password("1234")
            .name("김건영")
            .city("경기도")
            .district("용인시")
            .pictureUri("/image/profile/kim")
            .type(MemberType.CLIENT)
            .position(PositionType.MF)
            .disclosureScopeState(DisclosureScopeState.PUBLIC)
            .build();

    public static MemberSignUpDto jeoung = MemberSignUpDto.builder()
            .id("jeoung@gmail.com")
            .password("1234")
            .name("정승현")
            .city("경기도")
            .district("부천시")
            .pictureUri("/image/profile/jeoung")
            .type(MemberType.CLIENT)
            .position(PositionType.GK)
            .disclosureScopeState(DisclosureScopeState.PUBLIC)
            .build();

    public static Member toAdminEntity(MemberSignUpDto dto){
        return Member.builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .name(dto.getName())
                .roleSet(Set.of(MemberRole.ADMIN, MemberRole.CLIENT))
                .city(dto.getCity())
                .district(dto.getDistrict())
                .pictureUri(dto.getPictureUri())
                .position(dto.getPosition())
                .build();
    }

    public static Member toClientEntity(MemberSignUpDto dto){
        return Member.builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .name(dto.getName())
                .roleSet(Set.of(MemberRole.CLIENT))
                .city(dto.getCity())
                .district(dto.getDistrict())
                .pictureUri(dto.getPictureUri())
                .position(dto.getPosition())
                .build();
    }
}
