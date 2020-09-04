package com.chun.apps.member.dtos;


import com.chun.commons.enums.DisclosureScopeState;
import com.chun.commons.enums.PositionType;
import com.chun.crud.member.entitys.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberInfoDto {
    private String id;
    private String name;
    private String city;
    private String district;
    private String pictureUri;
    private PositionType position;
    private int likeCnt;
    private int rudeCnt;
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
