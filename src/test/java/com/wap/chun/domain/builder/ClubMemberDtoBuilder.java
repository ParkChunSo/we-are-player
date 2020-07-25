package com.wap.chun.domain.builder;

import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.domain.enums.PositionType;
import com.wap.chun.profile.club.dtos.ClubMemberDto;
import com.wap.chun.profile.member.dtos.MemberInfoDto;
import com.wap.chun.profile.member.dtos.MemberSignUpDto;

import java.time.LocalDateTime;

public class ClubMemberDtoBuilder {
    public static ClubMemberDto build(Member member, ClubMemberType type){
        return ClubMemberDto.builder()
                .memberId(member.getId())
                .memberName(member.getName())
                .city(member.getCity())
                .district(member.getDistrict())
                .registTime(LocalDateTime.now().toString())
                .pictureUri("picture/test/img.png")
                .position(PositionType.FW)
                .uniformNum(99)
                .type(type)
                .build();
    }
    public static ClubMemberDto build(MemberInfoDto member, ClubMemberType type){
        return ClubMemberDto.builder()
                .memberId(member.getId())
                .memberName(member.getName())
                .city(member.getCity())
                .district(member.getDistrict())
                .registTime(LocalDateTime.now().toString())
                .pictureUri("picture/test/img.png")
                .position(PositionType.FW)
                .uniformNum(99)
                .type(type)
                .build();
    }

    public static ClubMemberDto build(MemberSignUpDto member, ClubMemberType type){
        return ClubMemberDto.builder()
                .memberId(member.getId())
                .memberName(member.getName())
                .city(member.getCity())
                .district(member.getDistrict())
                .registTime(LocalDateTime.now().toString())
                .pictureUri("picture/test/img.png")
                .position(PositionType.FW)
                .uniformNum(99)
                .type(type)
                .build();
    }
}
