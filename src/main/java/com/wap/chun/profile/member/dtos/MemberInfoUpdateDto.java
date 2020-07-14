package com.wap.chun.profile.member.dtos;

import com.wap.chun.domain.enums.DisclosureScopeState;
import com.wap.chun.domain.enums.PositionType;
import lombok.Getter;

@Getter
public class MemberInfoUpdateDto {
    private String id;
    private String password;
    private String location;
    private String pictureUri;
    private PositionType position;
    private Integer likeCnt;
    private Integer rudeCnt;
    private DisclosureScopeState disclosureScopeState;
}
