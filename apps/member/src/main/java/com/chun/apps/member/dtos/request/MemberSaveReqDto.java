package com.chun.apps.member.dtos.request;


import com.chun.commons.enums.DisclosureScopeState;
import com.chun.commons.enums.MemberType;
import com.chun.commons.enums.PositionType;
import com.chun.crud.member.dtos.MemberSaveDto;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class MemberSaveReqDto {
    private String id;
    private String password;
    private String name;
    private String city;
    private String district;
    private PositionType position;
    private MemberType type;
    private DisclosureScopeState disclosureScopeState;

    public MemberSaveDto toMemberSaveDto(String pictureUri, String encodedPW){
        return MemberSaveDto.builder()
                .id(this.id)
                .password(encodedPW)
                .city(this.city)
                .district(this.district)
                .name(this.name)
                .position(this.position)
                .type(this.type)
                .disclosureScopeState(this.disclosureScopeState)
                .pictureUri(pictureUri)
                .build();
    }
}
