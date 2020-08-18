package com.wap.api.profile.member.dtos;

import com.wap.api.profile.domain.enums.DisclosureScopeState;
import com.wap.api.profile.domain.enums.PositionType;
import lombok.Getter;

@Getter
public class MemberInfoUpdateDto {
    private String id;
    private String password;
    private String city;
    private String district;
    private String pictureUri;
    private PositionType position;
    private int likeCnt;
    private int rudeCnt;
    private DisclosureScopeState disclosureScopeState;
}
