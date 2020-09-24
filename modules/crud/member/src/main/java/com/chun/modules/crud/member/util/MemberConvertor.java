package com.chun.modules.crud.member.util;

import com.chun.commons.dtos.member.MemberResDto;
import com.chun.modules.crud.member.entitys.Member;

public class MemberConvertor {
    public static MemberResDto toMemberResDto(Member member){
        return MemberResDto.builder()
                .id(member.getId())
                .name(member.getName())
                .city(member.getCity())
                .district(member.getDistrict())
                .pictureUri(member.getPictureUri())
                .position(member.getPosition())
                .disclosureScopeState(member.getDisclosureScopeState())
                .likeCnt(member.getLikeCnt())
                .rudeCnt(member.getRudeCnt())
                .build();
    }
}
