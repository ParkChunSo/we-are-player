package com.chun.crud.dtos;

import com.chun.commons.enums.DisclosureScopeState;
import com.chun.commons.enums.PositionType;
import lombok.Getter;

@Getter
public class MemberUpdateDto {
    private String id;
    private String city;
    private String district;
    private String pictureUri;
    private PositionType position;
    private int likeCnt;
    private int rudeCnt;
    private DisclosureScopeState disclosureScopeState;
}
