package com.wap.chun.domain;

import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.enums.DisclosureScopeState;
import com.wap.chun.domain.enums.MemberRole;
import com.wap.chun.domain.enums.PositionType;
import com.wap.chun.profile.member.dtos.MemberSignUpDto;
import lombok.Getter;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Getter
public class MemberInfoSetUp {

    public static MemberSignUpDto park = MemberSignUpDto.builder()
            .id("park@gmail.com")
            .password("1234")
            .name("박춘소")
            .location("경기도 양평군")
            .pictureUri("/image/profile/park")
            .position(PositionType.FW)
            .disclosureScopeState(DisclosureScopeState.PUBLIC)
            .build();

    public static MemberSignUpDto yun = MemberSignUpDto.builder()
            .id("yun@gmail.com")
            .password("12345")
            .name("김윤상")
            .location("경기도 광주시")
            .pictureUri("/image/profile/yun")
            .position(PositionType.DF)
            .disclosureScopeState(DisclosureScopeState.PUBLIC)
            .build();

    public static MemberSignUpDto kim = MemberSignUpDto.builder()
            .id("kim@gmail.com")
            .password("1234")
            .name("김건영")
            .location("경기도 용인시")
            .pictureUri("/image/profile/kim")
            .position(PositionType.MF)
            .disclosureScopeState(DisclosureScopeState.PUBLIC)
            .build();

    public static MemberSignUpDto jeoung = MemberSignUpDto.builder()
            .id("jeoung@gmail.com")
            .password("1234")
            .name("정승현")
            .location("경기도 부천시")
            .pictureUri("/image/profile/jeoung")
            .position(PositionType.GK)
            .disclosureScopeState(DisclosureScopeState.PUBLIC)
            .build();

    public static Member toAdminEntity(MemberSignUpDto dto){
        return Member.builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .name(dto.getName())
                .roleSet(Set.of(MemberRole.ADMIN, MemberRole.CLIENT))
                .location(dto.getLocation())
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
                .location(dto.getLocation())
                .pictureUri(dto.getPictureUri())
                .position(dto.getPosition())
                .build();
    }
}
