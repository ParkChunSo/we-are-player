package com.chun.crud.member.dtos;

import com.chun.commons.enums.DisclosureScopeState;
import com.chun.commons.enums.MemberType;
import com.chun.commons.enums.PositionType;
import lombok.Getter;

@Getter
public class MemberSaveDto {
    private String id;
    private String password;
    private String name;
    private String city;
    private String district;
    private String pictureUri;
    private PositionType position;
    private MemberType type;
    private DisclosureScopeState disclosureScopeState;
}
