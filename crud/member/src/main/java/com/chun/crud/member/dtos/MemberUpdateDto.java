package com.chun.crud.member.dtos;

import com.chun.commons.enums.DisclosureScopeState;
import com.chun.commons.enums.PositionType;
import com.chun.crud.member.entitys.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateDto {
    private String id;
    private String city;
    private String district;
    private String pictureUri;
    private PositionType position;
    private int likeCnt;
    private int rudeCnt;
    private DisclosureScopeState disclosureScopeState;

    public MemberUpdateDto(Member member) {
        this.id = member.getId();
        this.city = member.getCity();
        this.district = member.getDistrict();
        this.pictureUri = member.getPictureUri();
        this.position = member.getPosition();
        this.likeCnt = member.getLikeCnt();
        this.rudeCnt = member.getRudeCnt();
        this.disclosureScopeState = member.getDisclosureScopeState();
    }
}
