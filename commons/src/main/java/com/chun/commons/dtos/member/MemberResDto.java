package com.chun.commons.dtos.member;


import com.chun.commons.enums.DisclosureScopeState;
import com.chun.commons.enums.PositionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor
public class MemberResDto {
    private String id;
    private String name;
    private String city;
    private String district;
    private String pictureUri;
    private PositionType position;
    private int likeCnt;
    private int rudeCnt;
    private DisclosureScopeState disclosureScopeState;
}
