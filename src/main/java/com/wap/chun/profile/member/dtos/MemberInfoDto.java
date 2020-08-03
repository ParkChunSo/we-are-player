package com.wap.chun.profile.member.dtos;

import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.enums.DisclosureScopeState;
import com.wap.chun.domain.enums.PositionType;
import lombok.Getter;

@Getter
public class MemberInfoDto {
    private String id;
    private String name;
    private String city;
    private String district;
    private String pictureUri;
    private PositionType position;
    private Integer likeCnt;
    private Integer rudeCnt;
    private DisclosureScopeState disclosureScopeState;

    public MemberInfoDto(Member entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.city = entity.getCity();
        this.district = entity.getDistrict();
        this.pictureUri = entity.getPictureUri();
        this.position = entity.getPosition();
        this.likeCnt = entity.getLikeCnt();
        this.rudeCnt = entity.getRudeCnt();
        this.disclosureScopeState = entity.getDisclosureScopeState();
    }
}
