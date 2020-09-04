package com.chun.apps.member.dtos;


import com.chun.commons.enums.DisclosureScopeState;
import com.chun.commons.enums.PositionType;
import com.chun.crud.member.dtos.MemberUpdateDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberUpdateReqDto {
    private String id;
    private String city;
    private String district;
    private PositionType position;
    private int likeCnt;
    private int rudeCnt;
    private DisclosureScopeState disclosureScopeState;

    public MemberUpdateDto toMemberUpdateDto(String pictureUri){
        return MemberUpdateDto.builder()
                .id(this.id)
                .city(this.city)
                .district(this.district)
                .position(this.position)
                .pictureUri(pictureUri)
                .disclosureScopeState(this.disclosureScopeState)
                .likeCnt(this.likeCnt)
                .rudeCnt(this.rudeCnt)
                .build();
    }
}
